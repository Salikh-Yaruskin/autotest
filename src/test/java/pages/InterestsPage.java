package pages;

import domain.api.PreviewResponse;
import helpers.PropertyProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilUrlContains;
import static mapper.PreviewMapper.getPreview;
import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class InterestsPage extends BasePage {

    @FindBy(css = "#form-views input[value='xbox']")
    private WebElement xboxRadio;

    @FindBy(css = "a[ui-sref='form.payment']")
    private WebElement nextSectionToPaymentButton;

    @FindBy(css = "#status-buttons a[ui-sref='.payment']")
    private WebElement paymentTopButton;

    @FindBy(css = "pre.ng-binding")
    private WebElement preField;

    public InterestsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что шаг Interests открыт")
    public boolean isOpened() {
        waitUntilVisible(webDriver, xboxRadio);
        return xboxRadio.isDisplayed();
    }

    @Step("Выбрать интерес: I like XBOX")
    public InterestsPage selectXbox() {
        waitUntilClickable(webDriver, xboxRadio);
        xboxRadio.click();
        return this;
    }

    @Step("Проверить, что radio XBOX выбран")
    public boolean isXboxSelected() {
        waitUntilVisible(webDriver, xboxRadio);
        return xboxRadio.isSelected();
    }

    @Step("Получить введенные значение из первью-поля")
    public PreviewResponse getPreviewField() {
        waitUntilVisible(webDriver, preField);
        return getPreview(preField.getText().trim());
    }

    @Step("Перейти на Payment через верхнее меню")
    public PaymentPage goToPaymentViaTopBar() {
        waitUntilClickable(webDriver, paymentTopButton);
        paymentTopButton.click();
        waitUntilUrlContains(webDriver, PropertyProvider.getInstance().getProperty("web.url.payment"));
        return new PaymentPage(webDriver);
    }

    @Step("Перейти на Payment кнопкой Next Section")
    public PaymentPage clickNextSectionToPayment() {
        waitUntilVisible(webDriver, nextSectionToPaymentButton);
        nextSectionToPaymentButton.click();
        return new PaymentPage(webDriver);
    }
}
