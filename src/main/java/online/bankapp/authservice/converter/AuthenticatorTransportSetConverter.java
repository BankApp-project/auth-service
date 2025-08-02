package online.bankapp.authservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.web.webauthn.api.AuthenticatorTransport;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class AuthenticatorTransportSetConverter implements AttributeConverter<Set<AuthenticatorTransport>, String> {

    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(Set<AuthenticatorTransport> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream()
                .map(AuthenticatorTransport::getValue)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<AuthenticatorTransport> convertToEntityAttribute(String transports) {
        if (transports == null || transports.isBlank()) {
            return null;
        }
        var transportsArray = transports.split(DELIMITER);

        return Arrays.stream(transportsArray)
                .map(AuthenticatorTransport::valueOf)
                .collect(Collectors.toSet());
    }
}
