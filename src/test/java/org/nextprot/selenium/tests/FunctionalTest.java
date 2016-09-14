package org.nextprot.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static junit.framework.Assert.assertTrue;

/**
 * Created by dteixeira on 19/08/16.
 */
public class FunctionalTest extends BaseSeleniumTest{


    @Test
    public void shouldGetDescriptionOfInsulinAfterASearch() throws IOException {

        getDriver().get("https://www.nextprot.org");
        WebElement searchField = getDriver().findElement(By.id("search-query"));
        searchField.sendKeys("insulin");
        searchField.submit();

        assertTrue("The source code should contain a description about the insulin",
                (new WebDriverWait(getDriver(), 10)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getPageSource().contains("Insulin decreases blood glucose concentration");
                    }
                })
        );

    }
}
