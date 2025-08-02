package online.bankapp.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //TODO resolve what to do with .logout
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        //sessions stored in Redis (to meet microservices / SCS needs)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/webauthn/**",
                                "/actuator/health"
                        ).permitAll()
                        .anyRequest().authenticated())
                .webAuthn(webAuthn -> webAuthn
                        .rpName("BankApp")
                        .rpId("bankapp.online")
                        .allowedOrigins("https://bankapp.online"))
                .build();

    }
}
