package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DefaultWindowPage;
import pages.FramesAndWindowsPage;

import static org.testng.Assert.assertEquals;

@Epic("Way2Automation")
@Feature("Frames and Windows")
public class FramesAndWindowsTest extends BasicTest {

    @Test
    @Story("Открытие нескольких вкладок")
    @Description("Открыть новую вкладку, перейти в неё, открыть третью и проверить количество окон")
    @Severity(SeverityLevel.CRITICAL)
    public void openMultipleWindowsTest() throws InterruptedException {
        webDriver.get(PropertyProvider.getInstance().getProperty("tables.url"));

        int actual = new FramesAndWindowsPage(webDriver)
                .switchToFrame()
                .clickOpenWindow()
                .clickNewBrowserTab()
                .getOpenedWindowsCount();

        int expected = 3;

        assertEquals(actual, expected);
    }
}
