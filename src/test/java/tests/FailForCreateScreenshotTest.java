package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.ProfilePage;
import tests.processing.RetryAnalyzer;

import java.util.Objects;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Epic("Way2Automation")
@Feature("Скриншоты при намеренном падение тестов")
public class FailForCreateScreenshotTest extends BasicTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Намеренно падающий тест - проверяем неправильный URL")
    @Severity(SeverityLevel.TRIVIAL)
    void demoFail_wrongUrl_shouldAttachScreenshot() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        assertTrue(Objects.requireNonNull(webDriver.getCurrentUrl()).contains("#/form/payment"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Намеренно падающий тест - неверный текст alert")
    @Severity(SeverityLevel.MINOR)
    void demoFail_wrongAlertText_shouldAttachScreenshot() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        String actual = new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .clickNextSection()
                .selectXbox()
                .goToPaymentViaTopBar()
                .clickSubmitAndGetAlertText();

        String expected = "not awesome!";

        assertEquals(expected, actual);
    }

}
