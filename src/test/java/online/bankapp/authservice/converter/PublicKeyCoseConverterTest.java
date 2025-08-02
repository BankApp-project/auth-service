package online.bankapp.authservice.converter;

import org.junit.jupiter.api.Test;
import org.springframework.security.web.webauthn.api.ImmutablePublicKeyCose;
import org.springframework.security.web.webauthn.api.PublicKeyCose;

import static org.junit.jupiter.api.Assertions.*;

class PublicKeyCoseConverterTest {

    @Test
    void convertToDatabaseColumn_WithNullAttribute_ReturnsNull() {
        // Arrange
        PublicKeyCoseConverter converter = new PublicKeyCoseConverter();

        // Act
        byte[] result = converter.convertToDatabaseColumn(null);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToDatabaseColumn_WithValidPublicKeyCose_ReturnsBytes() {
        // Arrange
        PublicKeyCose publicKeyCose = new ImmutablePublicKeyCose(new byte[]{1, 2, 3});
        PublicKeyCoseConverter converter = new PublicKeyCoseConverter();

        // Act
        byte[] result = converter.convertToDatabaseColumn(publicKeyCose);

        // Assert
        assertNotNull(result);
        assertArrayEquals(new byte[]{1, 2, 3}, result);
    }

    @Test
    void convertToEntityAttribute_WithNullDbData_ReturnsNull() {
        // Arrange
        PublicKeyCoseConverter converter = new PublicKeyCoseConverter();

        // Act
        PublicKeyCose result = converter.convertToEntityAttribute(null);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_WithValidBytes_ReturnsPublicKeyCose() {
        // Arrange
        byte[] dbData = new byte[]{1, 2, 3};
        PublicKeyCoseConverter converter = new PublicKeyCoseConverter();

        // Act
        PublicKeyCose result = converter.convertToEntityAttribute(dbData);

        // Assert
        assertNotNull(result);
        assertArrayEquals(new byte[]{1, 2, 3}, result.getBytes());
    }
}