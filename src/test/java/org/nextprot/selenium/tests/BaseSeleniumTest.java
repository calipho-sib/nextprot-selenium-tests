package org.nextprot.selenium.tests;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Create by Daniel Teixeira
 */
public class BaseSeleniumTest {

    private String baseUrl;
    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void openBrowser() {

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();

        desiredCapabilities.setCapability("webdriver.chrome.args", Arrays.asList("--whitelisted-ips=192.33.215.52"));

        System.setProperty("webdriver.chrome.driver", "/home/local/selenium/chromedriver");
        driver = new ChromeDriver(desiredCapabilities);
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
