package tests;

import domain.api.BrowserType;
import helpers.DriverFactory;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({AllureTestNg.class, AllureScreenshotListener.class})
public class BasicTest {

    protected WebDriver webDriver;

    @BeforeClass
    @Parameters({"browser", "useGrid"})
//    @Step("Инициализация WebDriver")
    void init(@Optional("CHROME") String browser, @Optional("false") boolean useGrid) {
        webDriver = DriverFactory.createWebDriver(BrowserType.valueOf(browser.toUpperCase()), useGrid);
    }

    @AfterClass
//    @Step("Закрытие WebDriver")
    void end() {
        webDriver.quit();
    }
}
