package com.verivox.assignment.commons;

import com.verivox.assignment.Configuration;
import com.verivox.assignment.driver.DriverFactory;
import com.verivox.assignment.pages.PageFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.verivox.assignment.utils.CommonUtils.setBrowser;
import static com.verivox.assignment.utils.CommonUtils.setOs;
import static com.verivox.assignment.utils.CommonUtils.setUrl;

public class Hooks {
    private WebDriver driver;

    @Before
    public void initialiseTest() {

        String browser = setBrowser();
        String url = setUrl();
        String os = setOs();
        driver = DriverFactory.getNewDriver(os, browser);
        driver.get(url);
        PageFactory.updateDriver(driver);
    }

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.close();
        driver.quit();
    }
}
