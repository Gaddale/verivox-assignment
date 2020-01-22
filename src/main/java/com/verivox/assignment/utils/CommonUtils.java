package com.verivox.assignment.utils;

import com.verivox.assignment.Configuration;
import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils {

    public static String createRamdomString() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    public static String createRandomFirstLastName() {
        return RandomStringUtils.random(10, true, false);
    }

    public static Integer createRandomNumber() {
        return Integer.valueOf(RandomStringUtils.randomNumeric(1));
    }

    public static String setBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = System.getenv("browser");
            if (browser == null) {
                browser = Configuration.BROWSER.getValue();
            }
        }
        return browser;
    }

    public static String setUrl() {
        String url = System.getProperty("url");
        if (url == null) {
            url = System.getenv("url");
            if (url == null) {
                url = Configuration.TEST_ENV.getValue();
            }
        }
        return url;
    }

    public static String setOs(){
        String os = System.getProperty("os");
        if (os == null) {
            os = System.getenv("url");
            if (os == null) {
                os = Configuration.OS.getValue();
            }
        }
        return os;
    }

}
