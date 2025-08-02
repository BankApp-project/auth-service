package online.bankapp.authservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialType;

@Converter(autoApply = true)
public class PublicKeyCredentialTypeConverter implements AttributeConverter<PublicKeyCredentialType, String> {

    @Override
    public String convertToDatabaseColumn(PublicKeyCredentialType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public PublicKeyCredentialType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return PublicKeyCredentialType.valueOf(dbData);
    }
}
