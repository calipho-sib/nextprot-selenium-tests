package org.nextprot.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Create by Daniel Teixeira
 */
public class BaseSeleniumTest {

    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    
    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void openBrowser() {

    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();

    	capabilities.setCapability("webdriver.chrome.args", Arrays.asList("--whitelisted-ips=192.33.215.52"));

        try {
			driver = new RemoteWebDriver(new URL("http://jenkins.vital-it.ch:4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
        
        screenshotHelper = new ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshot.png");
        driver.quit();
    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}
