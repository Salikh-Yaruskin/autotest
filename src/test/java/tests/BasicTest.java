package tests;

import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

import static helpers.PropertyProvider.getInstance;

@Listeners({AllureTestNg.class, AllureScreenshotListener.class})
public class BasicTest {

    protected WebDriver webDriver;

    @BeforeClass
    @Parameters({"browser"})
    @Step("Инициализация WebDriver через Selenium Grid")
    void init(@Optional("chrome") String browser) throws MalformedURLException {
        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--incognito");
            options.addArguments("--headless");

            webDriver = new RemoteWebDriver(new URL(getInstance().getProperty("hub.url")), options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            webDriver = new RemoteWebDriver(new URL(getInstance().getProperty("hub.url")), options);
        }
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end() {
        webDriver.quit();
    }
}
