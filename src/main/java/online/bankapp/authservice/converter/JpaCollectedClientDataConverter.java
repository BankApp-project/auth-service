package online.bankapp.authservice.converter;

import com.webauthn4j.converter.CollectedClientDataConverter;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.client.CollectedClientData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NonNull;
import online.bankapp.authservice.exception.ConverterException;

@Converter(autoApply = true)
public class JpaCollectedClientDataConverter implements AttributeConverter<CollectedClientData, byte[]> {

    private final CollectedClientDataConverter converter = new CollectedClientDataConverter(new ObjectConverter());

    @Override
    public byte[] convertToDatabaseColumn(@NonNull CollectedClientData attribute) {
        byte[] res;
        try {
            res = converter.convertToBytes(attribute);
        } catch (RuntimeException e) {
            throw new ConverterException("Failed to convert CollectedClientData to database column", e);
        }

        return res;
    }

    @Override
    public CollectedClientData convertToEntityAttribute(byte[] dbData) {
        CollectedClientData res;
        try {
            res = converter.convert(dbData);
        } catch (RuntimeException e) {
            throw new ConverterException("Failed to convert database column to CollectedClientData", e);
        }

        return res;
    }
}
