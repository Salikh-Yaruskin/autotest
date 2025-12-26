package mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.api.PreviewResponse;

public class PreviewMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static PreviewResponse getPreview(String response) {
        PreviewResponse preview;
        try {
            preview = mapper.readValue(response, PreviewResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return preview;
    }
}
