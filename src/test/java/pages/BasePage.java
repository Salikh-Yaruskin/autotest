package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        try {
            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
