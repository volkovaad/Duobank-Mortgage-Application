package stepDefinitions.api.alina;

import io.cucumber.java.en.Given;
import stepDefinitions.SharedData;

public class getAppStepDefs {

    SharedData sharedData;

    public getAppStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;}

    @Given("the user  provide a valid mortgage id {int}")
    public void the_user_provide_a_valid_mortgage_id(Integer int1) {

        sharedData.getRequestSpecification().queryParam("id", int1);
    }
}
