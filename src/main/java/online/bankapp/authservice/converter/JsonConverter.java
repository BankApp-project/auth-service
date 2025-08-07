package online.bankapp.authservice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.exception.ConverterException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonConverter {

    private final JsonMapper mapper;

    public String serialize(@NonNull Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ConverterException("Failed to serialize object to JSON", e);
        }
    }

    public <T> T deserialize(@NonNull String json, @NonNull Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new ConverterException("Failed to deserialize JSON to object", e);
        }
    }
}
