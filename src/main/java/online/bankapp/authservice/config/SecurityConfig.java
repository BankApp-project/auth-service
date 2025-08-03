package online.bankapp.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.webauthn.registration.HttpSessionPublicKeyCredentialCreationOptionsRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    //TODO resolve what to do with .logout
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http

    /// /                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session
//                        //sessions stored in Redis (to meet microservices / SCS needs)
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers(
//                                "/webauthn/**",
//                                "/actuator/health"
//                        ).permitAll()
//                        .anyRequest().authenticated())
//                .webAuthn(webAuthn -> webAuthn
//                        .rpName("BankApp")
//                        .rpId("bankapp.online")
//                        .allowedOrigins("https://bankapp.online")
//                        )
//                .build();
//
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ...
        http
                // ...
                .formLogin(withDefaults())
                .webAuthn((webAuthn) -> webAuthn
                        .rpName("Spring Security Relying Party")
                        .rpId("localhost")
                        .allowedOrigins("http://localhost:8080")
                        .creationOptionsRepository(new HttpSessionPublicKeyCredentialCreationOptionsRepository())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
