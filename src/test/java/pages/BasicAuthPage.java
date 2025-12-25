package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilVisible;

public class BasicAuthPage extends BasePage {

    @FindBy(xpath = "//div[@class='exampleBox']//input[contains(@id, 'displayImage')]")
    private WebElement displayImageButton;

    @FindBy(xpath = "//div[@class='exampleBox']//img[@id='downloadImg']")
    private WebElement authenticatedImage;

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку Display Image")
    public BasicAuthPage clickDisplayImage() {
        waitUntilVisible(webDriver, displayImageButton);
        displayImageButton.click();
        return this;
    }

    @Step("Проверить, что изображение загружено")
    public boolean isImageLoaded() {
        return authenticatedImage.getAttribute("src") != null
                && !authenticatedImage.getAttribute("src").isEmpty();
    }
}
