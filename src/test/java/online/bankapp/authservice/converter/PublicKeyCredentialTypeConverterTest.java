package online.bankapp.authservice.converter;

import org.junit.jupiter.api.Test;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PublicKeyCredentialTypeConverterTest {

    @Test
    void convertToDatabaseColumn_withNonNullAttribute_shouldReturnCorrectStringValue() {
        // Arrange
        PublicKeyCredentialTypeConverter converter = new PublicKeyCredentialTypeConverter();
        PublicKeyCredentialType attribute = PublicKeyCredentialType.PUBLIC_KEY;

        // Act
        String result = converter.convertToDatabaseColumn(attribute);

        // Assert
        assertEquals("public-key", result);
    }

    @Test
    void convertToDatabaseColumn_withNullAttribute_shouldReturnNull() {
        // Arrange
        PublicKeyCredentialTypeConverter converter = new PublicKeyCredentialTypeConverter();

        // Act
        String result = converter.convertToDatabaseColumn(null);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_withValidDbData_shouldReturnCorrespondingEnum() {
        // Arrange
        PublicKeyCredentialTypeConverter converter = new PublicKeyCredentialTypeConverter();
        String dbData = "public-key";

        // Act
        PublicKeyCredentialType result = converter.convertToEntityAttribute(dbData);

        // Assert
        assertEquals(PublicKeyCredentialType.PUBLIC_KEY, result);
    }

    @Test
    void convertToEntityAttribute_withNullDbData_shouldReturnNull() {
        // Arrange
        PublicKeyCredentialTypeConverter converter = new PublicKeyCredentialTypeConverter();

        // Act
        PublicKeyCredentialType result = converter.convertToEntityAttribute(null);

        // Assert
        assertNull(result);
    }
}