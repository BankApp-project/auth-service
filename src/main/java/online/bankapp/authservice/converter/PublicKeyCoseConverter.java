package online.bankapp.authservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.web.webauthn.api.ImmutablePublicKeyCose;
import org.springframework.security.web.webauthn.api.PublicKeyCose;

@Converter(autoApply = true)
public class PublicKeyCoseConverter implements AttributeConverter<PublicKeyCose, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(PublicKeyCose attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getBytes();
    }

    @Override
    public PublicKeyCose convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }

        return new ImmutablePublicKeyCose(dbData);
    }
}
