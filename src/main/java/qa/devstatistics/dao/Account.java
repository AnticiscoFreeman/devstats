package qa.devstatistics.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import qa.devstatistics.helpers.AccountRole;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 28.09.2023
 */

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String name;
    private boolean active;

    @ElementCollection(targetClass = AccountRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public boolean isAdminRole() {
        return roles.contains(AccountRole.ADMIN);
    }

    public boolean isUserRole() {
        return roles.contains(AccountRole.USER);
    }

    public boolean isGuestRole() {
        return roles.contains(AccountRole.GUEST);
    }
}
