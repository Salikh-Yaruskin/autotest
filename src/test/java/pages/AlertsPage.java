package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilVisible;

public class AlertsPage extends BasePage {

    @FindBy(css = "a[href='#example-1-tab-2']")
    private WebElement inputAlertTab;

    @FindBy(css = "#example-1-tab-2 iframe[class='demo-frame']")
    private WebElement inputAlertFrame;

    @FindBy(xpath = "//button[contains(text(), 'Input')]")
    private WebElement showPromptButton;

    @FindBy(id = "demo")
    private WebElement resultText;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключиться на вкладку Input Alert")
    public AlertsPage openInputAlertTab() {
        waitUntilVisible(webDriver, inputAlertTab);
        inputAlertTab.click();
        return this;
    }

    @Step("Переключиться в iframe Input Alert")
    public AlertsPage switchToInputAlertFrame() {
        webDriver.switchTo().defaultContent();
        waitUntilVisible(webDriver, inputAlertFrame);
        webDriver.switchTo().frame(inputAlertFrame);
        return this;
    }

    @Step("Ввести текст в Input Alert: {text}")
    public AlertsPage enterTextToPrompt(String text) {
        waitUntilVisible(webDriver, showPromptButton);
        showPromptButton.click();

        Alert alert = webDriver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();

        return this;
    }

    @Step("Получить текст результата")
    public String getResultText() {
        return resultText.getText();
    }
}