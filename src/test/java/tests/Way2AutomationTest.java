package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pages.InterestsPage;
import pages.PaymentPage;
import pages.ProfilePage;
import tests.processing.RetryAnalyzer;

import static helpers.WaitHelper.waitUntilUrlContains;

@Epic("Way2Automation")
@Feature("Мульти-форма регистрации")
public class Way2AutomationTest extends BasicTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-1 Ввод данных в поля Name и Email")
    @Description("Тест проверяет возможность заполнения полей имени и почты")
    void addCustomerTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));
        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.inputName();
        profilePage.inputEmail();
        String response = profilePage.getPreviewField();
        PreviewResponse preview;
        try {
            preview = mapper.readValue(response, PreviewResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        softAssert.assertEquals(preview.name(), PropertyProvider.getInstance().getProperty("property.name"));
        softAssert.assertEquals(preview.email(), PropertyProvider.getInstance().getProperty("property.email"));
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-2 Переход с Profile на Interests")
    @Description("Проверка перехода на шаг Interests по кнопке Next Section")
    void goToInterestsTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.inputName();
        profilePage.inputEmail();

        profilePage.clickNextSection();

        softAssert.assertEquals(webDriver.getCurrentUrl(),
                PropertyProvider.getInstance().getProperty("web.url.interests"));
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("ТС-3 Выбор интереса")
    @Description("Проверка выбора radio-button 'I like XBOX'")
    void selectXboxInterestTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        ProfilePage profilePage = new ProfilePage(webDriver);
        InterestsPage interestsPage = profilePage
                .inputName()
                .inputEmail()
                .clickNextSection();

        interestsPage.selectXbox();

        PreviewResponse preview = getPreview(interestsPage.getPreviewField());

        softAssert.assertTrue(interestsPage.isXboxSelected());
        softAssert.assertEquals(preview.type(), "xbox");

        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("ТС-4 Переход на Payment через статус-бар")
    @Description("Проверка перехода на шаг Payment по кнопке сверху PAYMENT")
    void goToPaymentViaTopBarTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        ProfilePage profilePage = new ProfilePage(webDriver);
        InterestsPage interestsPage = profilePage
                .inputName()
                .inputEmail()
                .clickNextSection();

        PaymentPage paymentPage = interestsPage.goToPaymentViaTopBar();

        waitUntilUrlContains(webDriver, PropertyProvider.getInstance().getProperty("web.url.payment"));

        softAssert.assertEquals(webDriver.getCurrentUrl(),
                PropertyProvider.getInstance().getProperty("web.url.payment"));
        softAssert.assertTrue(paymentPage.isOpened());

        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.BLOCKER)
    @Story("ТС-5 Завершение сценария")
    @Description("Проверка появления alert 'awesome!' после клика Submit на Payment")
    void submitShowsAwesomeAlertTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.profile"));

        ProfilePage profilePage = new ProfilePage(webDriver);
        InterestsPage interestsPage = profilePage
                .inputName()
                .inputEmail()
                .clickNextSection();

        interestsPage.selectXbox();

        PaymentPage paymentPage = interestsPage.goToPaymentViaTopBar();

        String alertText = paymentPage.clickSubmitAndGetAlertText();

        softAssert.assertEquals(alertText,
                PropertyProvider.getInstance().getProperty("property.payment.message-alert"));
        softAssert.assertAll();
    }

    private PreviewResponse getPreview(String response) {
        PreviewResponse preview;
        try {
            preview = mapper.readValue(response, PreviewResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return preview;
    }
}
