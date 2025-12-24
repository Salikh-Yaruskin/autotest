package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.api.CookiesResponse;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CookiesHelper {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Step("Сохранить cookies в файл: {file}")
    public static void saveCookies(WebDriver webDriver, Path file) {
        try {
            Files.createDirectories(file.getParent());

            Set<Cookie> cookies = webDriver.manage().getCookies();

            List<CookiesResponse> cookiesResponses = cookies.stream()
                    .map(cookie -> new CookiesResponse(
                            cookie.getName(),
                            cookie.getValue(),
                            cookie.getDomain(),
                            cookie.getPath(),
                            cookie.getExpiry() == null ? null : cookie.getExpiry().getTime(),
                            cookie.isSecure(),
                            cookie.isHttpOnly(),
                            cookie.getSameSite()
                    ))
                    .toList();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file.toFile(), cookiesResponses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Загрузить cookies из файла: {file}")
    public static void loadCookies(WebDriver driver, String baseUrl, Path file) {
        try {
            driver.get(baseUrl);

            List<CookiesResponse> list = objectMapper.readValue(file.toFile(), new TypeReference<>() {});

            for (CookiesResponse cookie : list) {
                Cookie.Builder builder = new Cookie.Builder(cookie.name(), cookie.value())
                        .path(cookie.path() == null ? "/" : cookie.path())
                        .isSecure(cookie.secure())
                        .isHttpOnly(cookie.httpOnly());

                if (cookie.expiryEpochMs() != null) {
                    builder.expiresOn(new Date(cookie.expiryEpochMs()));
                }

                driver.manage().addCookie(builder.build());
            }

        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить cookies: " + file, e);
        }
    }

    @Step("Удаление файла с куками")
    public static void deleteCookies(Path file) {
        if (!Files.exists(file)) {
            return;
        }

        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
