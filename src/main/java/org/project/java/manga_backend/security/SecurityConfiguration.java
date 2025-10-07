package org.project.java.manga_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // filter chain with new syntax using lambda expressions
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/manga/create").hasAuthority("ADMIN")
                .requestMatchers("/manga/edit", "/manga/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/authors/create").hasAuthority("ADMIN")
                .requestMatchers("/authors/edit", "/authors/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/genres/create").hasAuthority("ADMIN")
                .requestMatchers("/genres/edit", "/genres/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/status/create").hasAuthority("ADMIN")
                .requestMatchers("/status/edit", "/status/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                .requestMatchers("/manga", "/manga/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll())

                .formLogin(Customizer.withDefaults())
                .cors(cors -> cors.disable()) // disabilita CORS
                .csrf(csrf -> csrf.disable()); // disabilita CSRF

        return http.build();
    }

    // userDetailsService
    @Bean
    UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    // password encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // auth provider
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        // authProvider.setUserDetailsService)(;
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
