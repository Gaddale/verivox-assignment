package com.verivox.assignment.driver;

import com.verivox.assignment.Configuration;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static BrowserConfig config;
    private static WebDriver driver;

    public static WebDriver getNewDriver(String os, String browser) {
        setBrowserInfo(os, browser);
        setDriver();
        configureDriver();
        return driver;
    }

    private static void configureDriver() {
        driver.manage().window().maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(Long.parseLong(Configuration.IMPLICIT_WAIT.getValue()), TimeUnit.SECONDS);
    }

    private static void setDriver() {
        driver = config.getDriver();
    }

    private static void setBrowserInfo(String os, String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                if (os.equals("linux")) {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/linux/chromedriver");
                }else{
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/windows/chromedriver.exe");
                }
                config = new ChromeConfig();
                break;
            case "firefox":
                if(os.equals("linux")){
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/linux/geckodriver");
                }else{
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/windows/geckodriver.exe");
                }
                config = new FirefoxConfig();
                break;
        }
    }
}
