package domain.api;

public record CookiesResponse(
        String name,
        String value,
        String domain,
        String path,
        Long expiryEpochMs,
        boolean secure,
        boolean httpOnly,
        String sameSite
) {
}
