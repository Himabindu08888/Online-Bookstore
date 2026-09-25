package com.bookstore.app.config;

import com.bookstore.app.model.Role;
import com.bookstore.app.model.User;
import com.bookstore.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Creates a default admin account on first startup so you can log in
 * to /admin immediately without manually inserting a row.
 *
 * Default login: admin@bookstore.com / admin123
 * Change the password after first login, or remove this class in production.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String adminEmail = "admin@bookstore.com";
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User("Store Admin", adminEmail, passwordEncoder.encode("admin123"), Role.ROLE_ADMIN);
            userRepository.save(admin);
            System.out.println(">>> Default admin created: " + adminEmail + " / admin123");
        }
    }
}
