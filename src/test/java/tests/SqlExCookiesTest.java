package tests;

import helpers.CookiesHelper;
import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SqlExMainPage;
import tests.processing.RetryAnalyzer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static helpers.PropertyProvider.getInstance;

@Epic("SQL-EX")
@Feature("Авторизация по cookies")
public class SqlExCookiesTest extends BasicTest {

    private static final Path COOKIE_FILE = Paths.get("target", "cookies", "sql-ex-cookies.json");

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Story("Первый запуск — логин, второй — reuse cookies")
    @Description("Если файла cookies нет — логинимся и сохраняем. Если есть — подставляем cookies и проверяем, " +
            "что залогинены.")
    @Severity(SeverityLevel.CRITICAL)
    public void loginOrReuseCookiesTest() {
        SoftAssert softAssert = new SoftAssert();

        String baseUrl = getInstance().getProperty("web.url.sqlex");
        webDriver.get(baseUrl);

        SqlExMainPage mainPage = new SqlExMainPage(webDriver);

        if (Files.exists(COOKIE_FILE)) {
            CookiesHelper.loadCookies(webDriver, baseUrl, COOKIE_FILE);
            webDriver.navigate().refresh();
            CookiesHelper.deleteCookies(COOKIE_FILE);
        } else {
            mainPage.login(getInstance().getProperty("sqlex.name"), getInstance().getProperty("sqlex.password"));
            CookiesHelper.saveCookies(webDriver, COOKIE_FILE);
            softAssert.assertTrue(Files.exists(COOKIE_FILE));
        }

        softAssert.assertAll();
    }
}
