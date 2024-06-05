package com.nhnacademy.minidooraydgateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin ->
                                formLogin
                                        .loginPage("/login")
                                        .usernameParameter("id")
                                        .passwordParameter("password")
                                        .loginProcessingUrl("/login/process")
                                        .defaultSuccessUrl("/home")
//                                .successHandler(successHandler)
//                                .failureHandler(failureHandler)
                                        .permitAll()
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/home")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/", "/home").permitAll()
//                                .requestMatchers("/member/**").hasAnyAuthority("ROLE_MEMBER")
                                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
