package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;

public class BasicTest {

    protected WebDriver webDriver;

    @BeforeClass
    @Step("Открытие страницы")
    void init() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--headless");

        String selenoidUrl = System.getenv()
                .getOrDefault("SELENOID_URL", "http://selenoid:4444/wd/hub");

        webDriver = new RemoteWebDriver(
                new URL(selenoidUrl),
                options
        );
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end() {
        webDriver.quit();
    }
}
