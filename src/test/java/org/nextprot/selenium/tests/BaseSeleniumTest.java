package org.nextprot.selenium.tests;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Create by Daniel Teixeira
 */
public abstract class BaseSeleniumTest {

    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void openBrowser() {

        Properties props = readPropertiesFromFile("nextprot-selenium.properties");

        System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability("webdriver.chrome.args", props.getProperty("webdriver.chrome.args"));

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

    private static Properties readPropertiesFromFile(String filename) {

        Properties props = new Properties();

        try (InputStream input = new FileInputStream(filename)) {

            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return props;
    }
}
