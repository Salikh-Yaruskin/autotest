package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DroppablePage extends BasePage {

    @FindBy(css = "#example-1-tab-1 iframe.demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    public DroppablePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Переключиться в iframe")
    public DroppablePage switchToFrame() {
        webDriver.switchTo().frame(iframe);
        return this;
    }

    @Step("Перетащить элемент")
    public DroppablePage dragAndDrop() {
        new Actions(webDriver)
                .dragAndDrop(draggable, droppable)
                .perform();
        return this;
    }

    @Step("Получить текст droppable")
    public String getDroppableText() {
        return droppable.getText();
    }
}
