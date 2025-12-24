package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilAlert;
import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//*[@id='form-views']//h3[contains(.,'Test Completed')]")
    private WebElement completedHeader;

    @FindBy(xpath = "//*[@id='form-views']//button[@type='submit' and contains(.,'Submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='status-buttons']//a[contains(@class,'active') and contains(@href,'#/form/payment')]")
    private WebElement paymentActiveTab;

    public PaymentPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что Payment открыт")
    public boolean isOpened() {
        waitUntilVisible(webDriver, completedHeader);
        return completedHeader.isDisplayed();
    }

    @Step("Проверить, что Payment активен в статус-баре")
    public boolean isPaymentActive() {
        waitUntilVisible(webDriver, paymentActiveTab);
        return paymentActiveTab.isDisplayed();
    }

    @Step("Нажать Submit и получить текст alert")
    public String clickSubmitAndGetAlertText() {
        waitUntilClickable(webDriver, submitButton);
        submitButton.click();

        Alert alert = waitUntilAlert(webDriver);
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
