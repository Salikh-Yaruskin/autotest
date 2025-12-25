package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AlertsPage;

import static helpers.PropertyProvider.getInstance;
import static org.testng.AssertJUnit.assertTrue;

@Epic("Way2Automation")
@Feature("Alerts")
public class AlertsTest extends BasicTest {

    @Test
    @Story("Input Alert")
    @Description("Ввод текста в Input Alert и проверка результата")
    @Severity(SeverityLevel.CRITICAL)
    public void inputAlertTest() {
        webDriver.get(getInstance().getProperty("alerts.url"));

        AlertsPage page = new AlertsPage(webDriver);

        page.openInputAlertTab()
                .switchToInputAlertFrame()
                .enterTextToPrompt(getInstance().getProperty("alerts.message"));

        assertTrue(page.getResultText().contains(getInstance().getProperty("alerts.message")));
    }
}
