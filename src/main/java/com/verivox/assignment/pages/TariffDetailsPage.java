package com.verivox.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TariffDetailsPage extends BasePage {

    private By tariffPrice = By.xpath("(//div[@class='centered-content effective-price-wrapper']/div)[1]");
    private By fiveMinutenOnlineWechselnButton = By.xpath("//div[@class='summary-tariff-content flex']//a[@class='responsive-label-txt offer-page-cta']");

    public TariffDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTariffPrice(){
        waitForElementToLoad(tariffPrice);
        return getElement(tariffPrice);
    }

    public WebElement getFiveMinutenOnlineWechselnButton(){
        return getElement(fiveMinutenOnlineWechselnButton);
    }
}
