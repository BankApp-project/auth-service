package online.bankapp.authservice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.bankapp.authservice.exception.ConverterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JsonConverterTest {

    private final JsonConverter jsonConverter = new JsonConverter(new JsonMapper());

    /**
     * Tests the {@code deserialize} method of {@code JsonConverter} for its ability to process valid JSON
     * and convert it into the specified object type.
     */
    @Test
    void givenValidJson_thenDeserializeToObject() {
        // Arrange
        String validJson = "{\"name\":\"Test\",\"value\":123}";
        TestObject expectedObject = new TestObject("Test", 123);

        // Act
        TestObject actualObject = jsonConverter.deserialize(validJson, TestObject.class);

        // Assert
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void givenInvalidJson_thenThrowConverterException() throws JsonProcessingException {
        // Arrange
        JsonMapper mapper = mock(JsonMapper.class);
        JsonConverter jsonConverter = new JsonConverter(mapper);
        String invalidJson = "{\"name\":\"Test\",\"value\":}";
        when(mapper.readValue(invalidJson, TestObject.class)).thenThrow(new JsonProcessingException("Invalid JSON") {
        });

        // Act & Assert
        ConverterException exception = assertThrows(ConverterException.class,
                () -> jsonConverter.deserialize(invalidJson, TestObject.class));

        assertEquals("Failed to deserialize JSON to object", exception.getMessage());
        verify(mapper, times(1)).readValue(invalidJson, TestObject.class);
    }

    @Test
    void givenNullJson_thenThrowNullPointerException() {
        // Arrange
        JsonMapper mapper = mock(JsonMapper.class);
        JsonConverter jsonConverter = new JsonConverter(mapper);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> jsonConverter.deserialize(null, TestObject.class));
    }

    @Test
    void givenNullTargetClass_thenThrowNullPointerException() {
        // Arrange
        JsonMapper mapper = mock(JsonMapper.class);
        JsonConverter jsonConverter = new JsonConverter(mapper);
        String validJson = "{\"name\":\"Test\",\"value\":123}";

        // Act & Assert
        assertThrows(NullPointerException.class, () -> jsonConverter.deserialize(validJson, null));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TestObject {
        private String name;
        private int value;
    }
}