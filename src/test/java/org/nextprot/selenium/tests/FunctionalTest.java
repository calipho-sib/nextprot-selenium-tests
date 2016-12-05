package org.nextprot.selenium.tests;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class FunctionalTest extends BaseSeleniumTest {

    @Test
    public void shouldGetDescriptionOfInsulinAfterASearch() throws IOException {

        getDriver().get("https://www.nextprot.org");
        WebElement searchField = getDriver().findElement(By.id("search-query"));
        searchField.sendKeys("insulin");
        searchField.submit();

        new WebDriverWait(getDriver(), 10).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getPageSource().contains("Insulin decreases blood glucose concentration");
                }
            });
    }

    @Test
    @Ignore
    public void shouldGetDescriptionOfAnAdvancedSearch() throws IOException {

        getDriver().get("https://www.nextprot.org");

        WebElement advSearchButton = getDriver().findElement(By.id("advanced-search"));
        advSearchButton.click();

        new WebDriverWait(getDriver(), 20).until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(WebDriver driver) {

                String query = "select distinct ?entry where {" +
                        "  ?entry :isoform ?iso. " +
                        "  ?iso :keyword / :term cv:KW-0597. " +
                        "  ?iso :cellularComponent /:term /:childOf cv:SL-0086. " +
                        "}";

                String script = "angular.element('[ng-controller=SearchCtrl]').scope().Search.params.sparql=\"" + query +"\";";

                if (driver instanceof JavascriptExecutor) {

                    ((JavascriptExecutor) driver).executeScript(script);
                    driver.findElement(By.id("search-button")).submit();

                    new WebDriverWait(getDriver(), 10).until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return d.getPageSource().contains("Regulator that plays a central role in regulation of apoptosis");
                        }
                    });
                }

                return true;
            }
        });
    }
}
