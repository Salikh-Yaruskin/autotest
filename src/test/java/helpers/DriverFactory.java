package helpers;

import domain.api.BrowserType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverFactory {

    public static WebDriver createWebDriver(BrowserType type, boolean useGrid) {
        if (useGrid) {
            return createRemoteDriver(type);
        } else {
            return createLocalDriver(type);
        }
    }

    private static WebDriver createRemoteDriver(BrowserType type) {
        Capabilities capabilities;
        switch (type) {
            case CHROME -> capabilities = new ChromeOptions();
            case FIREFOX -> capabilities = new FirefoxOptions();
            case EDGE -> capabilities = new EdgeOptions();
            case IE -> capabilities = createIEOptions();
            default -> throw new IllegalArgumentException("Unknown browser");
        }

        try {
            return new RemoteWebDriver(new URL(PropertyProvider.getInstance().getProperty("hub.url")), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static WebDriver createLocalDriver(BrowserType type) {
        switch (type) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--incognito");
                options.addArguments("--headless");
                return new ChromeDriver(options);
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                return new FirefoxDriver(options);
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                return new EdgeDriver(options);
            }
            case IE -> {
                InternetExplorerOptions options = createIEOptions();
                return new InternetExplorerDriver(options);
            }
            default -> throw new IllegalArgumentException("Unknown browser");
        }
    }

    private static InternetExplorerOptions createIEOptions() {
        InternetExplorerOptions ieo = new InternetExplorerOptions();

        ieo.ignoreZoomSettings();
        ieo.introduceFlakinessByIgnoringSecurityDomains();

        ieo.setCapability("ignoreProtectedModeSettings", true);

        return ieo;
    }
}
