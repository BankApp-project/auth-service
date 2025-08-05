package online.bankapp.authservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Authentication Service of BankApp API Documentation")
                        .version("0.0.1")
                        .description("API documentation for the Authentication Service of BankApp Project.")
                        .contact(new Contact()
                                .name("BankApp")
                                .url("https://github.com/BankApp-project/auth-service")
                                .email("contact@bankapp.online")
                        )
                );
    }
}
