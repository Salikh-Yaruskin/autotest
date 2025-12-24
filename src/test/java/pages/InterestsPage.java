package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class InterestsPage extends BasePage {

    @FindBy(xpath = "//*[@id='form-views']//div[@class='radio']//label[contains(normalize-space(.),'I like XBOX')]")
    private WebElement xboxLabel;

    @FindBy(xpath = "//*[@id='form-views']//input[@type='radio' and @value='xbox']")
    private WebElement xboxRadio;

    @FindBy(xpath = "//*[@id='form-views']//a[contains(@href,'#/form/payment') or @ui-sref='form.payment']")
    private WebElement nextSectionToPaymentButton;

    @FindBy(xpath = "//*[@id='status-buttons']//a[contains(@href,'#/form/payment')]")
    private WebElement paymentTopButton;

    @FindBy(xpath = "//pre[contains(@class,'ng-binding')]")
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
        waitUntilClickable(webDriver, xboxLabel);
        xboxLabel.click();
        return this;
    }

    @Step("Проверить, что radio XBOX выбран")
    public boolean isXboxSelected() {
        waitUntilVisible(webDriver, xboxRadio);
        return xboxRadio.isSelected();
    }

    @Step("Получить введенные значение из первью-поля")
    public String getPreviewField() {
        waitUntilVisible(webDriver, preField);
        return preField.getText().trim();
    }

    @Step("Перейти на Payment через верхнее меню")
    public PaymentPage goToPaymentViaTopBar() {
        waitUntilClickable(webDriver, paymentTopButton);
        paymentTopButton.click();
        return new PaymentPage(webDriver);
    }

    @Step("Перейти на Payment кнопкой Next Section")
    public PaymentPage clickNextSectionToPayment() {
        waitUntilVisible(webDriver, nextSectionToPaymentButton);
        nextSectionToPaymentButton.click();
        return new PaymentPage(webDriver);
    }
}
