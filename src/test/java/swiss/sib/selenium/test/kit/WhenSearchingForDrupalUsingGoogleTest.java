/**
 * Created by vioannid on 23/05/16.
 */
package swiss.sib.selenium.test.kit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class WhenSearchingForDrupalUsingGoogleTest extends BaseSeleniumTest {

    @Test
    public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {

        getDriver().get("http://www.google.com");

        assertEquals("The page title should equal Google at the start of the test.", "Google", getDriver().getTitle());
        WebElement searchField = getDriver().findElement(By.name("q"));
        searchField.sendKeys("Drupal!");
        searchField.submit();
        assertTrue("The page title should start with the search string after the search.",
                (new WebDriverWait(getDriver(), 10)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().startsWith("drupal!");
                    }
                })
        );
    }

}