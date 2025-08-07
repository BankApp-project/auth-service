package online.bankapp.authservice.converter;

import com.webauthn4j.converter.CollectedClientDataConverter;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.client.CollectedClientData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.exception.ConverterException;

@RequiredArgsConstructor
@Converter(autoApply = true)
public class JpaCollectedClientDataConverter implements AttributeConverter<CollectedClientData, byte[]> {

    private static final CollectedClientDataConverter CONVERTER = new CollectedClientDataConverter(new ObjectConverter());

    @Override
    public byte[] convertToDatabaseColumn(@NonNull CollectedClientData attribute) {
        byte[] res;
        try {
            res = CONVERTER.convertToBytes(attribute);
        } catch (RuntimeException e) {
            throw new ConverterException("Failed to convert CollectedClientData to database column", e);
        }

        return res;
    }

    @Override
    public CollectedClientData convertToEntityAttribute(byte[] dbData) {
        CollectedClientData res;
        try {
            res = CONVERTER.convert(dbData);
        } catch (RuntimeException e) {
            throw new ConverterException("Failed to convert database column to CollectedClientData", e);
        }

        return res;
    }
}
