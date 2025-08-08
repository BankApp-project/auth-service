package online.bankapp.auth.infrastructure.converter.passkey;

import com.webauthn4j.converter.util.CborConverter;
import com.webauthn4j.data.attestation.statement.AttestationStatement;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter(autoApply = true)
public class AttestationStatementConverter implements AttributeConverter<AttestationStatement, byte[]> {

    private final CborConverter cborConverter;

    @Override
    public byte[] convertToDatabaseColumn(@NonNull AttestationStatement attribute) {
        try {
            var envelope = new AttestationStatementEnvelope(attribute);
            return cborConverter.writeValueAsBytes(envelope);
        } catch (Exception e) {
            throw new ConverterException("Failed to convert AttestationStatement to database column", e);
        }
    }

    @Override
    public AttestationStatement convertToEntityAttribute(byte[] dbData) {
        if (dbData == null || dbData.length == 0) {
            return null;
        }

        try {
            var deserializedData = cborConverter.readValue(dbData, AttestationStatementEnvelope.class);
            return deserializedData.getAttestationStatement();
        } catch (Exception e) {
            throw new ConverterException("Failed to convert database column to AttestationStatement", e);
        }
    }
}