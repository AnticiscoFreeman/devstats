package qa.devstatistics.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dto.AccountFormHelper;
import qa.devstatistics.helpers.AccountRole;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.repos.AccountRepo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 28.09.2023
 */

@Service
public class AccountService implements UserDetailsService {

    private final Status status;

    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final StringBuilder tempMessage;

    public AccountService(Status status, AccountRepo accountRepo, PasswordEncoder passwordEncoder) {
        this.status = status;
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        this.tempMessage = new StringBuilder();
    }

    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findByUsername(username);
    }

    public Status createNewAccount(String username, String password) {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            return status.badStatus("Account exist");
        }

        account = new Account();
        account.setActive(true);
        account.setUsername(username);
        account.setName(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setRoles(Collections.singleton(AccountRole.GUEST));
        accountRepo.save(account);

        return status.goodStatus(
                String.format("Account %s (%s) create success",
                        account.getUsername(),
                        Arrays.toString(account.getRoles().toArray()))
        );
    }

    public Status updateAccount(AccountFormHelper accountFormHelper) {
        Account accountFromDb = accountRepo.findById(accountFormHelper.getId());

        tempMessage.delete(0, tempMessage.length());

        for (String key : accountFormHelper.getFormData().keySet()) {
            if (key.equals("deleteAccount")) {
                accountRepo.deleteById(accountFormHelper.getId());
                tempMessage.append("AccountID ").append(accountFormHelper.getId())
                        .append(" Username ").append(accountFromDb.getUsername())
                        .append(" deleted complete");
                return status.goodStatus(tempMessage.toString());
            }
        }

        tempMessage.append("AccountID ").append(accountFormHelper.getId()).append(" ");

        if (!accountFromDb.getUsername().equals(accountFormHelper.getUsername())) {
            accountFromDb.setUsername(accountFormHelper.getUsername());
            tempMessage.append("change username to [").append(accountFormHelper.getUsername()).append("], ");
        }

        if (accountFormHelper.getPassword() != null && !accountFormHelper.getPassword().isEmpty()) {
            accountFromDb.setPassword(passwordEncoder.encode(accountFormHelper.getPassword()));
            tempMessage.append("setup new password [")
                    .append(accountFormHelper.getPassword())
                    .append("], ");
        }

        if (!accountFromDb.getName().equals(accountFormHelper.getName())) {
            accountFromDb.setName(accountFormHelper.getName());
            tempMessage.append("name to [").append(accountFormHelper.getName()).append("], ");
        }

        Set<String> allRoles = Arrays.stream(AccountRole.values())
                .map(AccountRole::name)
                .collect(Collectors.toSet());

        accountFromDb.getRoles().clear();

        for (String key : accountFormHelper.getFormData().values()) {
            if (allRoles.contains(key)) {
                accountFromDb.getRoles().add(AccountRole.valueOf(key));
                tempMessage.append("role ");
            }
        }
        accountRepo.save(accountFromDb);
        tempMessage.append("save complete");
        return status.goodStatus(tempMessage.toString());
    }
}
