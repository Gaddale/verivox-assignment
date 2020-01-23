package com.verivox.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TariffPage extends BasePage {

    private By mehrZumTarifButton = By.xpath("(//div[@class=\"row my-4\"]/descendant::app-tariff-cta)[1]");
    private By searchResults = By.cssSelector("[class=\"row my-4\"]>div>app-tariff");
    private By tariffPrice = By.xpath("(//div[@class=\"row my-4\"]/descendant::app-tariff-price/div/div[@class=\"position-relative large mb-2\"]/strong)[1]");
    private By weitereTarifeLadenButton = By.xpath("//button[contains(text(),'weitere tarife laden')]");
    private String searchResultsSting = "[class=\"row my-4\"]>div>app-tariff";
    
    public TariffPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchResults() {
        visibilityOfElementLocatedCss(searchResultsSting);
        return getElement(searchResults);
    }

    public List<WebElement> getAllSearchResults(){
        return elementsList(searchResults);
    }

    public WebElement getMehrZumTarifButton(){
        return getElement(mehrZumTarifButton);
    }

    public WebElement getTariffPrice(){
        return getElement(tariffPrice);
    }

    public WebElement getWeitereTarifeLadenButton(){
        return getElement(weitereTarifeLadenButton);
    }

    public List<WebElement> tarifeLadenButton(){
        return elementsList(weitereTarifeLadenButton);
    }

    public void waitForResultsPageLoad(){
        visibilityOfElementLocatedCss(searchResultsSting);
        getSearchResults();
        getMehrZumTarifButton();
    }
}
