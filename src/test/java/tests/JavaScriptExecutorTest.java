package tests;

import helpers.JavaScriptExecutorHelper;
import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProfilePage;
import tests.processing.RetryAnalyzer;

@Epic("JavaScriptExecutor")
@Feature("Функции JavaScriptExecutor")
public class JavaScriptExecutorTest extends BasicTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("ТС-1 Удаление фокуса и проверка скролла")
    @Description("Тест проверяет снятие фокуса с поля ввода и определение скролла на странице")
    void blurFocusAndCheckScrollTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        new ProfilePage(webDriver).inputName("Ivan");

        JavaScriptExecutorHelper.blurActiveElement(webDriver);

        String actual = webDriver.switchTo().activeElement().getTagName().toLowerCase();
        String expect = "input";

        boolean hasScroll = JavaScriptExecutorHelper.hasVerticalScroll(webDriver);

        softAssert.assertNotEquals(actual, expect);
        softAssert.assertTrue(hasScroll);

        softAssert.assertAll();
    }
}
