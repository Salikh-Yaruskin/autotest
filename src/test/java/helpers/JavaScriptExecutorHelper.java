package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptExecutorHelper {

    private JavaScriptExecutorHelper() {
    }

    @Step("Убрать фокус с активного элемента через JavaScriptExecutor")
    public static void blurActiveElement(WebDriver driver) {
        ((JavascriptExecutor) driver)
                .executeScript("if (document.activeElement) { document.activeElement.blur(); }");
    }

    @Step("Проверить наличие вертикального скролла на странице через JavaScriptExecutor")
    public static boolean hasVerticalScroll(WebDriver driver) {
        Object result = ((JavascriptExecutor) driver)
                .executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
        if (result instanceof Boolean) {
            return (Boolean) result;
        }
        return Boolean.parseBoolean(String.valueOf(result));
    }
}
