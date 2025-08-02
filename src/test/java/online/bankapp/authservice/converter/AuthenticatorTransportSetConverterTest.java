package online.bankapp.authservice.converter;

import org.junit.jupiter.api.Test;
import org.springframework.security.web.webauthn.api.AuthenticatorTransport;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthenticatorTransportSetConverterTest {

    @Test
    void convertToDatabaseColumn_withValidSet() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        Set<AuthenticatorTransport> transports = Set.of(
                AuthenticatorTransport.BLE,
                AuthenticatorTransport.USB,
                AuthenticatorTransport.NFC
        );

        // Act
        String result = converter.convertToDatabaseColumn(transports);

        // Assert
        assertEquals("ble;usb;nfc", result);
    }

    @Test
    void convertToDatabaseColumn_withEmptySet() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        Set<AuthenticatorTransport> transports = Set.of();

        // Act
        String result = converter.convertToDatabaseColumn(transports);

        // Assert
        assertEquals("", result);
    }

    @Test
    void convertToDatabaseColumn_withNullSet() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();

        // Act
        String result = converter.convertToDatabaseColumn(null);

        // Assert
        assertEquals("", result);
    }

    @Test
    void convertToEntityAttribute_withValidString() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        String transports = "ble;usb;nfc";

        // Act
        Set<AuthenticatorTransport> result = converter.convertToEntityAttribute(transports);

        // Assert
        assertEquals(Set.of(
                AuthenticatorTransport.BLE,
                AuthenticatorTransport.USB,
                AuthenticatorTransport.NFC
        ), result);
    }

    @Test
    void convertToEntityAttribute_withEmptyString() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        String transports = "";

        // Act
        Set<AuthenticatorTransport> result = converter.convertToEntityAttribute(transports);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_withBlankString() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        String transports = "   ";

        // Act
        Set<AuthenticatorTransport> result = converter.convertToEntityAttribute(transports);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_withNullString() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();

        // Act
        Set<AuthenticatorTransport> result = converter.convertToEntityAttribute(null);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_withOneElementString() {
        // Arrange
        AuthenticatorTransportSetConverter converter = new AuthenticatorTransportSetConverter();
        String transports = "ble";

        // Act
        Set<AuthenticatorTransport> result = converter.convertToEntityAttribute(transports);

        // Assert
        assertEquals(Set.of(AuthenticatorTransport.BLE), result);
    }
}