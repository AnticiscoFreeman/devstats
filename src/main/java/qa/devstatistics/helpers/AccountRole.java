package qa.devstatistics.helpers;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 28.09.2023
 */

public enum AccountRole implements GrantedAuthority {
    GUEST,
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
