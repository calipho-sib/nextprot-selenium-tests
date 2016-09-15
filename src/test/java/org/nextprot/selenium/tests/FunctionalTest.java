package org.nextprot.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

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
            }
        );

    }

    /*@Test
    public void shouldGetDescriptionOfInsulinAfterAnAdvancedSearch() throws IOException {

        getDriver().get("https://www.nextprot.org");

        WebElement advSearchButton = getDriver().findElement(By.id("advanced-search"));
        advSearchButton.click();

        WebElement spanElement = getDriver().findElement(By.xpath("//div[@class=\"CodeMirror-code\"]//span/span"));
        spanElement.sendKeys("roudoudou");

        System.out.println("uiCodeMirror");

        //WebElement textArea = getDriver().findElement(By.id("search-query-advanced")).findElement(By.xpath("//textarea"));
        //System.out.println(textArea);

        //List<WebElement> textAreaList = getDriver().findElements(By.xpath("//ui-codemirror//textarea"));
        //System.out.println(textAreaList);

        //WebElement spanElement = getDriver().findElement(new By.ByXPath("//div[@class=CodeMirror-code]"));

        /*textArea.sendKeys("select distinct ?entry where {\n" +
                "  ?entry :isoform ?iso.\n" +
                "  ?iso :keyword / :term cv:KW-0597.\n" +
                "  ?iso :cellularComponent /:term /:childOf cv:SL-0086.\n" +
                "}");

        textArea.submit();

        new WebDriverWait(getDriver(), 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                return d.getPageSource().contains("Regulator that plays a central role in regulation of apoptosis");
            }
        });
    }
    */

}
