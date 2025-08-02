package online.bankapp.authservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.web.webauthn.api.Bytes;

@Converter(autoApply = true)
public class BytesConverter implements AttributeConverter<Bytes, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(Bytes attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getBytes();
    }

    @Override
    public Bytes convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        return new Bytes(dbData);
    }
}
