package qa.devstatistics.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dto.AccountFormHelper;
import qa.devstatistics.helpers.AccountRole;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.services.AccountService;

import java.util.Map;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 28.09.2023
 */

@Controller
@RequestMapping("/accounts")
@PreAuthorize("hasAuthority('ADMIN')")
public class AccountController {

    private String message = "";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public String getRegistrationPage(@AuthenticationPrincipal Account account,
                                      ModelMap model) {
        model.addAttribute("message", message.isEmpty() ? null : message);
        model.addAttribute("accountsList", accountService.getAllAccounts());
        model.addAttribute("account", account);
        model.addAttribute("allRolesList", AccountRole.values());
        return "accounts";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("username") String username,
                          @ModelAttribute("password") String password) {
        Status addAccountStatus = accountService.createNewAccount(username, password);
        message = addAccountStatus.getMessage();
        return "redirect:/accounts";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("username") String accountUsername,
                             @RequestParam("password") String accountPassword,
                             @RequestParam("name") String accountName,
                             @RequestParam("accountId") Account account,
                             @RequestParam Map<String, String> formData) {

        AccountFormHelper accountFormHelper = new AccountFormHelper(
                accountUsername,
                accountPassword,
                accountName,
                account.getId(),
                formData
        );

        Status updateAccountStatus = accountService.updateAccount(accountFormHelper);
        message = updateAccountStatus.getMessage();
        return "redirect:/accounts";
    }
}
