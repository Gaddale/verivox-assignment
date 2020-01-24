package com.verivox.assignment.steps;

import com.verivox.assignment.pages.HomePage;
import com.verivox.assignment.pages.PageFactory;
import com.verivox.assignment.pages.TariffDetailsPage;
import com.verivox.assignment.pages.TariffPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

import static org.assertj.core.api.Assertions.assertThat;

public class DSLCalculator {
    private static Logger logger = getLogger(DSLCalculator.class.getName());

    public String expectedTrafficPrice;
    public int resultsCount, initialCount = 20;
    private HomePage homePage = PageFactory.getInstance(HomePage.class);
    private TariffPage tariffPage = PageFactory.getInstance(TariffPage.class);
    private TariffDetailsPage tariffDetailsPage = PageFactory.getInstance(TariffDetailsPage.class);

    @Given("^the User is on www\\.verivox\\.de$")
    public void theUserIsOnWwwVerivoxDe() {
        assertThat(homePage.getHeader().isDisplayed()).isTrue();
        logger.info("Home Page launched");
    }

    @When("^he is on the DSL calculator$")
    public void heIsOnTheDSLCalculator() throws InterruptedException {
        Thread.sleep(2000);
        homePage.getDSLCalculator().click();
        logger.info("Clicked on DSL Calculator");
    }

    @And("^he enters prefix/code “Ihre Vorwahl” as (\\d+) with (\\d+) Mbit/s bandwidth selection$")
    public void heEntersPrefixCodeIhreVorwahlAsWithMbitSBandwidthSelection(int arg0, int arg1) {
        homePage.getPrefixInput().sendKeys("030");
        homePage.getBandWidth().click();
        logger.info("Prefix entered and bandwidth clicked");
    }

    @And("^clicks on the button labeled as \"([^\"]*)\"$")
    public void clicksOnTheButtonLabeledAs(String arg0) {
        homePage.getCompareNowButton().click();
        logger.info("Clicked on JETZT VERGLEICHEN Button");
    }

    @Then("^he should be able see the Result List page with all the available Tariffs$")
    public void heShouldBeAbleSeeTheResultListPageWithAllTheAvailableTariffs() {
        assertThat(tariffPage.getSearchResults().isDisplayed()).isTrue();
        List<WebElement> elements = tariffPage.getAllSearchResults();
        logger.info("Number of Search results displayed: " + elements.size());
        logger.info("-----------------------------------------------------------------------------------");
    }

    @Given("^the User is on the DSL Result List$")
    public void theUserIsOnTheDSLResultListFollowScenario() {
        tariffPage.getSearchResults();
        logger.info("Result list page loaded");
    }

    @When("^he selects one of the listed Tariffs$")
    public void heSelectsOneOfTheListedTariffs() {
        logger.info("Trariff listed selection");
    }

    @And("^clicks on mehr zum Tarif button$")
    public void clicksOnMehrZumTarifButton() {
        logger.info("Click on Tarif button");
        tariffPage.scrollDown();
        expectedTrafficPrice = tariffPage.getTariffPrice().getText().split(" ")[0];
        tariffPage.getMehrZumTarifButton().click();
    }

    @Then("^he should be able see the details of the selected Tariff$")
    public void heShouldBeAbleSeeTheDetailsOfTheSelectedTariff() {
        assertThat(tariffDetailsPage.getTariffPrice().getText()).contains(expectedTrafficPrice);
        logger.info("Tariff Details Page");
    }

    @And("^he should also see a button labeled as In (\\d+) Minuten online wechseln$")
    public void heShouldAlsoSeeAButtonLabeledAsInMinutenOnlineWechseln(int arg0) {
        assertThat(tariffDetailsPage.getFiveMinutenOnlineWechselnButton().isEnabled()).isTrue();
        logger.info("5 Minuten online wechseln Button is Displayed");
        logger.info("-----------------------------------------------------------------------------------");
    }

    @When("^there are more than (\\d+) tariffs available for the provided Vorwahl and bandwidth$")
    public void thereAreMoreThanTariffsAvailableForTheProvidedVorwahlAndBandwidth(int arg0) {
        logger.info("Condition is taken care in next step");
    }

    @Then("^verify user navigation with list appending of next set of tariffs and so on until all Tariffs are loaded$")
    public void verifyUserNavigationWithListAppendedWithNextSetOfTariffsAndSoOnUntilAllTariffsAreLoaded() throws InterruptedException {
        while (tariffPage.tarifeLadenButton().size() > 0) {
            tariffPage.scrollIntoView(tariffPage.getWeitereTarifeLadenButton());
            resultsCount = Integer.parseInt(tariffPage.getWeitereTarifeLadenButton().getText().split(" ")[0]);
            tariffPage.getWeitereTarifeLadenButton().click();
            tariffPage.waitForResultsPageLoad();
            Thread.sleep(4000);
            assertThat(tariffPage.getAllSearchResults().size()).isEqualTo(resultsCount + initialCount);
            initialCount = resultsCount + initialCount;
        }
        logger.info("All tariff results are loaded");
        logger.info("-----------------------------------------------------------------------------------");
    }
}

