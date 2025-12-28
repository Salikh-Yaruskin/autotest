package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.DroppablePage;
import tests.processing.RetryAnalyzer;

import static org.testng.AssertJUnit.assertEquals;

@Epic("Way2Automation")
@Feature("Interaction")
public class DroppableTest extends BasicTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Перетаскивание элемента в Droppable и проверка изменения текста")
    @Story("Drag and Drop")
    @Severity(SeverityLevel.NORMAL)
    public void dragAndDropShouldChangeText() {
        webDriver.get(PropertyProvider.getInstance().getProperty("drag-n-drop.url"));

        String actual = new DroppablePage(webDriver)
                .switchToFrame()
                .dragAndDrop()
                .getDroppableText();

        String expected = "Dropped!";

        assertEquals(expected, actual);
    }
}
