package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import tests.processing.RetryAnalyzer;

import static helpers.PropertyProvider.getInstance;

@Epic("Way2Automation")
@Feature("Вход в систему")
public class RegistrationTest extends BasicTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {
                        getInstance().getProperty("registeration.true.name"),
                        getInstance().getProperty("registeration.true.password"),
                        true
                },
                {
                        getInstance().getProperty("registeration.false.name"),
                        getInstance().getProperty("registeration.false.password"),
                        false
                }
        };
    }

    @Test(dataProvider = "loginData", retryAnalyzer = RetryAnalyzer.class)
    @Story("Универсальная авторизация через DataProvider")
    @Description("Один тест прогоняется с разными данными")
    @Severity(SeverityLevel.CRITICAL)
    void loginTest(String name, String password, boolean expectSuccess) {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(getInstance().getProperty("web.url.logi"));

        var loginPage = new LoginPage(webDriver)
                .setUsername(name)
                .setPassword(password)
                .setDescription(getInstance().getProperty("registeration.description"));

        if (expectSuccess) {
            var homePage = loginPage.clickLogin();
            softAssert.assertTrue(homePage.isOpened());
            softAssert.assertTrue(homePage.getLoggedInMessage()
                    .contains(getInstance().getProperty("registeration.homepage.message")));
            homePage.logout();
        } else {
            loginPage.clickLogin();
            softAssert.assertEquals(loginPage.getErrorMessage(),
                    getInstance().getProperty("registeration.error.message"));
        }

        softAssert.assertAll();
    }
}
