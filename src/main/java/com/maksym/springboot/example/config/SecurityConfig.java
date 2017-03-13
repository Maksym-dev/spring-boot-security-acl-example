package com.maksym.springboot.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Java configuration for Spring Security.
 */
@Profile("default")
@Configuration
@EnableWebSecurity
@ImportResource("classpath:/acl-context.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic();
        http
                .csrf().disable();
        http
                .requestCache().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/health**").permitAll()
                .antMatchers("/trace**").permitAll()
                .antMatchers("/logfile**").permitAll()
                .antMatchers("/env**").permitAll()
                .antMatchers("/beans**").permitAll()
                .antMatchers("/info**").permitAll()
                .antMatchers("/mappings**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/saml/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }
}
