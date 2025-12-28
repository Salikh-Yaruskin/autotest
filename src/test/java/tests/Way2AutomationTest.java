package tests;

import domain.api.PreviewResponse;
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

import static org.testng.AssertJUnit.assertEquals;

@Epic("Way2Automation")
@Feature("Мульти-форма регистрации")
public class Way2AutomationTest extends BasicTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-1 Ввод данных в поля Name и Email")
    @Description("Тест проверяет возможность заполнения полей имени и почты")
    void addCustomerTest() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        PreviewResponse actual = new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .getPreviewField();

        var expected = PreviewResponse.builder()
                .name("Ivan")
                .email("ivan@test.com")
                .build();

        assertEquals(actual, expected);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-2 Переход с Profile на Interests")
    @Description("Проверка перехода на шаг Interests по кнопке Next Section")
    void goToInterestsTest() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .clickNextSection();

        assertEquals(webDriver.getCurrentUrl(),
                PropertyProvider.getInstance().getProperty("web.url.interests"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("ТС-3 Выбор интереса")
    @Description("Проверка выбора radio-button 'I like XBOX'")
    void selectXboxInterestTest() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        PreviewResponse actual = new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .clickNextSection()
                .selectXbox()
                .getPreviewField();

        PreviewResponse expected = PreviewResponse.builder()
                .name("Ivan")
                .email("ivan@test.com")
                .type("xbox")
                .build();

        assertEquals(actual, expected);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-4 Переход на Payment через статус-бар")
    @Description("Проверка перехода на шаг Payment по кнопке сверху PAYMENT")
    void goToPaymentViaTopBarTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        boolean actual = new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .clickNextSection()
                .goToPaymentViaTopBar()
                .isOpened();

        boolean expected = true;

        softAssert.assertEquals(webDriver.getCurrentUrl(),
                PropertyProvider.getInstance().getProperty("web.url.payment"));
        softAssert.assertEquals(actual, expected);

        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.BLOCKER)
    @Story("ТС-5 Завершение сценария")
    @Description("Проверка появления alert 'awesome!' после клика Submit на Payment")
    void submitShowsAwesomeAlertTest() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        String actual = new ProfilePage(webDriver)
                .clearForm()
                .inputName("Ivan")
                .inputEmail("ivan@test.com")
                .clickNextSection()
                .selectXbox()
                .goToPaymentViaTopBar()
                .clickSubmitAndGetAlertText();

        String expect = PropertyProvider.getInstance().getProperty("property.payment.message-alert");

        assertEquals(actual, expect);
    }
}
