package pages;

import domain.api.PreviewResponse;
import helpers.PropertyProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static mapper.PreviewMapper.getPreview;
import static helpers.WaitHelper.waitUntilUrlContains;
import static helpers.WaitHelper.waitUntilVisible;

public class ProfilePage extends BasePage {

    @FindBy(css = "#form-views input[name='name']")
    private WebElement nameField;

    @FindBy(css = "#form-views input[name='email']")
    private WebElement emailField;

    @FindBy(css = "pre.ng-binding")
    private WebElement preField;

    @FindBy(css = "#form-views a[ui-sref='form.interests']")
    private WebElement nextSectionButton;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Заполнить поле Name")
    public ProfilePage inputName(String name) {
        waitUntilVisible(webDriver, nameField);
        if (name.isBlank()) {
            nameField.sendKeys(PropertyProvider.getInstance().getProperty("property.name"));
        } else {
            nameField.sendKeys(name);
        }
        return this;
    }

    @Step("Заполнить поле Email")
    public ProfilePage inputEmail(String email) {
        waitUntilVisible(webDriver, emailField);
        if (email.isBlank()) {
            emailField.sendKeys(PropertyProvider.getInstance().getProperty("property.email"));
        } else {
            emailField.sendKeys(email);
        }
        return this;
    }

    @Step("Получить введенные значение из первью-поля")
    public PreviewResponse getPreviewField() {
        waitUntilVisible(webDriver, preField);
        return getPreview(preField.getText().trim());
    }

    @Step("Перейти к шагу Interests")
    public InterestsPage clickNextSection() {
        waitUntilVisible(webDriver, nextSectionButton);
        nextSectionButton.click();
        waitUntilUrlContains(webDriver, PropertyProvider.getInstance().getProperty("web.url.interests"));
        return new InterestsPage(webDriver);
    }

    @Step("Очистить форму Profile")
    public ProfilePage clearForm() {
        waitUntilVisible(webDriver, nameField);
        nameField.clear();
        emailField.clear();
        return this;
    }
}
