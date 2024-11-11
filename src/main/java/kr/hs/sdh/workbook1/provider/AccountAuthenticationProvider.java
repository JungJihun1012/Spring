package kr.hs.sdh.workbook1.provider;

import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public final class AccountAuthenticationProvider extends DaoAuthenticationProvider {

    public AccountAuthenticationProvider() {
        super.hideUserNotFoundExceptions = false;
    }
}
