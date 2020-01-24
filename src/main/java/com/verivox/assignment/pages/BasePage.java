package com.verivox.assignment.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public abstract class BasePage {

    private static Logger logger = getLogger(BasePage.class.getName());

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
            return element;
        } catch (Exception e) {
            System.out.println("Some Error Occurred while creating Element" + locator.toString());
            e.printStackTrace();
        }
        return element;
    }

    public void waitForElementToLoad(final By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> elementsList(By locator) {
        return driver.findElements(locator);
    }

    public void visibilityOfElementLocatedCss(String css) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }

    public void visibilityOfElementLocatedXpath(String xpath) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement getXpath(By locator) {
        return driver.findElement(locator);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");

    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0,-100);", element);
    }

    public void waitForElementInvisibility(WebElement ele) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.invisibilityOf(ele));
        } catch (Exception e) {
            logger.info("Element Invisiblity Time out");
        }
    }

}
