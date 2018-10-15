package com.maksym.springboot.example;

import com.maksym.springboot.example.model.CustomUserDetails;
import com.maksym.springboot.example.model.User;
import com.maksym.springboot.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class.
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    /**
     * Method starts application.
     *
     * @param args input arguments from console.
     */
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        SpringApplication.exit(context);
    }

    @Bean
    CommandLineRunner lookup(UserService userService) {
        return args -> {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUsername("admin");
            customUserDetails.setPassword("password");
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails
                    .getPassword(), customUserDetails.getAuthorities()));
            userService.clear();
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setName("Ivan");
                System.out.println("Created user: " + userService.create(user));
            }
            System.out.println("All users:");
            userService.readAll().forEach(System.out::println);
            SecurityContextHolder.clearContext();
        };
    }
}
