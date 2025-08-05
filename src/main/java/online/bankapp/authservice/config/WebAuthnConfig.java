package online.bankapp.authservice.config;

import com.webauthn4j.converter.util.CborConverter;
import com.webauthn4j.converter.util.ObjectConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAuthnConfig {

    @Bean
    public CborConverter cborConverter() {
        return new ObjectConverter().getCborConverter();
    }
}
