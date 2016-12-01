/**
 * Created by vioannid on 23/05/16.
 */
package org.nextprot.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class SeoTest extends BaseSeleniumTest {

    @Test
    public void shouldGetCorrectTitleForPeptidePageInsm1() throws IOException {
        getDriver().get("https://www.nextprot.org/entry/NX_Q01101/peptides");
        assertEquals("The page title should equal INSM1 - Peptides", "INSM1 - Insulinoma-associated protein 1 - Peptides", getDriver().getTitle());
    }

/*
    @Test
    public void shouldGetCorrectMetaDescriptionForBrca2() throws IOException {

        getDriver().get("https://www.nextprot.org/entry/NX_P51587/medical");
        String description = getDriver().findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
        assertTrue(description.contains("NX_P51587 - BRCA2 - Breast cancer type 2 susceptibility protein - Medical."));
    }*/

}