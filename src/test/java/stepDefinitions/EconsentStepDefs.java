package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.EconsentPage;

public class EconsentStepDefs {

    EconsentPage econsentPage;
    @Given("I am on the Econsent page of the mortgage application")
    public void iAmOnTheEconsentPageOfTheMortgageApplication() {

        econsentPage = new EconsentPage();



    }
}
