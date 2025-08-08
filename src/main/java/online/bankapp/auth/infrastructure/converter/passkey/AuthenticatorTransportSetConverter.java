package online.bankapp.auth.infrastructure.converter.passkey;

import com.webauthn4j.data.AuthenticatorTransport;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class AuthenticatorTransportSetConverter implements AttributeConverter<Set<AuthenticatorTransport>, String> {

    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(Set<AuthenticatorTransport> attribute) {
        return attribute.stream()
                .map(AuthenticatorTransport::getValue)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<AuthenticatorTransport> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(DELIMITER))
                .map(AuthenticatorTransport::create)
                .collect(Collectors.toSet());
    }
}
