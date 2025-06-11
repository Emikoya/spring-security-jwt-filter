package com.example.demo;

import com.example.demo.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter toto) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello/public", "/auth/create-user", "/auth/create-admin", "/auth/create-super-admin", "/auth/login", "/h2-console/**").permitAll()
                        .requestMatchers("/hello/admin").hasRole("ADMIN")
                        .requestMatchers("/hello/super_admin").hasRole("SUPER_ADMIN")
                        .requestMatchers("/hello/user").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // utile pour afficher h2-console
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                .addFilterBefore(toto, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
