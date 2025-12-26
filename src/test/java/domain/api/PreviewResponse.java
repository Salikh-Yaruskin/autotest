package domain.api;

import lombok.Builder;

@Builder
public record PreviewResponse(String name,
                              String email,
                              String type) {
}
