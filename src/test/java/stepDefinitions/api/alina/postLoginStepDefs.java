package stepDefinitions.api.alina;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.SharedData;
import utilities.ConfigReader;
import utilities.DBUtils;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class postLoginStepDefs {

    SharedData sharedData;

    public postLoginStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the request is authenticated with a valid API key")
    public void the_request_is_authenticated_with_a_valid_api_key() {
        sharedData.getRequestSpecification().queryParam("api_key", ConfigReader.getProperty("api.key"));
    }
    @Given("the request body is set to the following payload as")
    public void the_request_body_is_set_to_the_following_payload_as(String string) {

        sharedData.setEmail(ConfigReader.getProperty("email"));
        sharedData.setPassword(ConfigReader.getProperty("password"));

        sharedData.getRequestSpecification().body(
                String.format(string, sharedData.getEmail(), sharedData.getPassword()));
    }
    @When("I send a {string} request to the endpoint {string}")
    public void i_send_a_request_to_the_endpoint(String requestType, String endpoint) {

        switch (requestType){
            case "GET" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(endpoint));
            case "POST" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().post(endpoint));
            case "PUT" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().put(endpoint));
            case "PATCH" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().patch(endpoint));
            case "DELETE" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().delete(endpoint));
        }


    }
    @Then("the response log should be displayed")
    public void the_response_log_should_be_displayed() {
        sharedData.getResponse().then().log().all();

    }
    @Then("the JWT token from the response is stored")
    public void the_jwt_token_from_the_response_is_stored() {
        String accessToken = sharedData.getResponse().path("access_token");
        sharedData.setJWToken(accessToken);

    }

    @And("the response body should contain a successfull message {string}")
    public void theResponseBodyShouldContainASuccessfullMessage (String string) {
        sharedData.getResponse().then().log().all().body("message", equalTo(string));
    }

    @And("the response body should contain the following text")
    public void theResponseBodyShouldContainTheFollowing(String string) {
        sharedData.getResponse().then().log().all()
                .body(containsString(String.format(string,sharedData.getJWToken())));
    }

    @And("the request body is set to the")
    public void theRequestBodyIsSetToThe(String string) {
        sharedData.getRequestSpecification().body(string);
    }

    @And("user_id should be extracted")
    public void user_idShouldBeExtracted() {
        String query = "SELECT user_id FROM tbl_mortagage WHERE b_email = '" + ConfigReader.getProperty("email") + "'";
        sharedData.setUser_id(DBUtils.getSingleIntValue(query));
        System.out.println(sharedData.getUser_id());
    }
}
