package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

import static org.testng.AssertJUnit.assertEquals;

@Epic("HTTP Gallery")
@Feature("Basic Authentication")
public class BasicAuthTest extends BasicTest {

    @Test
    @Story("Basic Auth через URL")
    @Description("Авторизация через HTTP Basic Auth и загрузка защищённого изображения")
    @Severity(SeverityLevel.CRITICAL)
    public void basicAuthTest() {
        webDriver.get(PropertyProvider.getInstance().getProperty("httpwatch.url"));

        boolean actual = new BasicAuthPage(webDriver)
                .clickDisplayImage()
                .isImageLoaded();

        boolean expected = true;

        assertEquals(expected, actual);
    }
}
