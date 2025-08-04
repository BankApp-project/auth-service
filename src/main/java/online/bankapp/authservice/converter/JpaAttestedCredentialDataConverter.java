package online.bankapp.authservice.converter;

import com.webauthn4j.converter.AttestedCredentialDataConverter;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JpaAttestedCredentialDataConverter implements AttributeConverter<AttestedCredentialData, byte[]> {

    private final AttestedCredentialDataConverter converter = new AttestedCredentialDataConverter(new ObjectConverter());

    @Override
    public byte[] convertToDatabaseColumn(AttestedCredentialData attribute) {
        return converter.convert(attribute);
    }

    @Override
    public AttestedCredentialData convertToEntityAttribute(byte[] dbData) {
        return converter.convert(dbData);
    }
}
