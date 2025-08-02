package online.bankapp.authservice.converter;

import org.junit.jupiter.api.Test;
import org.springframework.security.web.webauthn.api.Bytes;

import static org.junit.jupiter.api.Assertions.*;

class BytesConverterTest {

    /**
     * Unit tests for the BytesConverter class.
     * This class tests the behavior of the convertToDatabaseColumn method, which converts a Bytes object
     * into a byte array representation for database storage.
     */

    @Test
    void testConvertToDatabaseColumn_withValidBytes() {
        // Given
        BytesConverter converter = new BytesConverter();
        byte[] expectedBytes = new byte[]{1, 2, 3, 4};
        Bytes input = new Bytes(expectedBytes);

        // When
        byte[] result = converter.convertToDatabaseColumn(input);

        // Then
        assertArrayEquals(expectedBytes, result);
    }

    @Test
    void testConvertToDatabaseColumn_withNullBytes() {
        // Given
        BytesConverter converter = new BytesConverter();

        // When
        byte[] result = converter.convertToDatabaseColumn(null);

        // Then
        assertNull(result);
    }

    @Test
    void testConvertToEntityAttribute_withValidBytes() {
        BytesConverter converter = new BytesConverter();
        var bytes = new byte[]{1, 2, 3, 4};
        var expectedObj = new Bytes(bytes);

        //When
        var res = converter.convertToEntityAttribute(bytes);

        //Then
        assertEquals(res, expectedObj);
    }
}