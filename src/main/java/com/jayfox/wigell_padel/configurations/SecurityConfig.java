package com.jayfox.wigell_padel.configurations;

import com.jayfox.wigell_padel.entities.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf((csrf->csrf.disable()))
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers(
                                "/api/v5/customers",
                                "/api/v5/addfield",
                                "/api/v5/deletefield/**",
                                "/api/v5/updateinfo")
                        .hasRole("ADMIN")
                        .requestMatchers("/api/v5/availability",
                                "/api/v5/booking",
                                "/api/v5/mybookings",
                                "/api/v5/bookings/**")
                        .hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
                return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("user1")
                .roles("USER")
                .build();
        UserDetails userDetails2 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails userDetails3 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("user2")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails1,userDetails2,userDetails3);
    }

}
