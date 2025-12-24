package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriverWait wait;
    private static WaitHelper instance;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static synchronized WaitHelper getInstance(WebDriver drive){
        if (instance == null)
            instance = new WaitHelper(drive);
        return instance;
    }

    public WaitHelper(WebDriver driver){
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public static void waitUntilVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilUrlContains(WebDriver driver, String partOfUrl) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.urlContains(partOfUrl));
    }

    public static Alert waitUntilAlert(WebDriver driver) {
       return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.alertIsPresent());
    }
}
