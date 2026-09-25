package com.bookstore.app.config;

import com.bookstore.app.model.User;
import org.springframework.security.core.Authentication;

public class AuthUtil {

    private AuthUtil() {}

    public static User currentUser(Authentication authentication) {
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        return details.getUser();
    }
}
