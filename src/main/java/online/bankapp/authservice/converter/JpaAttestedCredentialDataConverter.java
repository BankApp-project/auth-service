package online.bankapp.authservice.converter;

import com.webauthn4j.converter.AttestedCredentialDataConverter;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.attestation.authenticator.AttestedCredentialData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class JpaAttestedCredentialDataConverter implements AttributeConverter<AttestedCredentialData, byte[]> {

    private static final AttestedCredentialDataConverter CONVERTER = new AttestedCredentialDataConverter(new ObjectConverter());

    @Override
    public byte[] convertToDatabaseColumn(AttestedCredentialData attribute) {
        return CONVERTER.convert(attribute);
    }

    @Override
    public AttestedCredentialData convertToEntityAttribute(byte[] dbData) {
        return CONVERTER.convert(dbData);
    }
}
