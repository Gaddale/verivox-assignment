package com.verivox.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By dslCalculator = By.xpath("//label[@id='mps-label-5']");
    private By homePageHeader = By.cssSelector("[class=\"hero-headline\"]");
    private By prefixCodeInput = By.name("Prefix");
    private By bandWidth = By.xpath("(//strong[text()='16 '])[1]");
    private By compareNowButton = By.xpath("//div[@id='mps-tab-box-5']//button[@class='page-button'][contains(text(),'Jetzt vergleichen')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getDSLCalculator(){
        return getXpath(dslCalculator);
    }

    public WebElement getHeader(){
        waitForElementToLoad(homePageHeader);
        return getElement(homePageHeader);
    }

    public WebElement getPrefixInput(){
        waitForElementToLoad(prefixCodeInput);
        return getElement(prefixCodeInput);
    }

    public WebElement getBandWidth(){
        waitForElementToLoad(bandWidth);
        return getElement(bandWidth);
    }

    public WebElement getCompareNowButton(){
        return getElement(compareNowButton);
    }
}
