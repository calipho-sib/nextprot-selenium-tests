package org.nextprot.selenium.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Create by Daniel Teixeira
 */
public abstract class BaseSeleniumTest {

    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;
    @Rule
    public TestName name = new TestName();

    
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

        //Properties props = readPropertiesFromFile("nextprot-selenium.properties");

        screenshotHelper = new ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot(name.getMethodName()+"_screenshot.png");
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
