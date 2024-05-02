package APItests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import stepDefinitions.SharedData;
import utilities.ConfigReader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PostUserLoginElshan {

    SharedData sharedData;

    public PostUserLoginElshan(SharedData sharedData) {
        this.sharedData = sharedData;
    }


    @Given("the request is authenticated with a valid API key")
    public void the_request_is_authenticated_with_a_valid_api_key() {
        sharedData.getRequestSpecification().
                queryParam("api_key", ConfigReader.getProperty("api.key"));
    }

    @Given("the request {string} header is set to {string}")
    public void the_request_header_is_set_to(String key, String value) {
        sharedData.getRequestSpecification().header(key, value);
    }

    @Given("the request body is set to the following payload as")
    public void the_request_body_is_set_to_the_following_payload_as(String payload) {
        sharedData.getRequestSpecification().body(payload);
    }

    @When("I send a {string} request to the endpoint {string}")
    public void i_send_a_request_to_the_endpoint(String requestType, String endpoint) {
        switch (requestType) {
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

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        sharedData.getResponse().then().statusCode(code);
    }

    @Then("the JWT token from the response is stored")
    public void the_jwt_token_from_the_response_is_stored() {
        String accessToken = sharedData.getResponse().path("access_token");

        System.out.println(accessToken);

        sharedData.setJWToken(accessToken);
    }

    @Given("API key is not provided")
    public void apiKeyIsNotProvided() {
        sharedData.getRequestSpecification().
                queryParam("api_key", ConfigReader.getProperty(""));
    }

    @And("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {

        sharedData.getResponse().then().body("message",
                equalTo("Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."));

    }

    @And("the response {string} header should be {string}")
    public void theResponseHeaderShouldBe(String key, String value) {
        sharedData.getResponse().then().header(key, value);
    }


    @And("API responds with a {int} status code and an error message {string}")
    public void apiRespondsWithAStatusCodeAndAnErrorMessage(int code, String message) {

        sharedData.getResponse().then().body("status", equalTo(code));
        sharedData.getResponse().then().body("message", equalTo(message));

    }

    @And("the JWT token from the response is generates and stored")
    public void theJWTTokenFromTheResponseIsGeneratesAndStored() {

        String accessToken = sharedData.getResponse().path("access_token");

        System.out.println(accessToken);

        sharedData.setJWToken(accessToken);
    }

    @And("Success message should be {string}")
    public void successMessageShouldBe(String message) {
        sharedData.getResponse().then().body("message", equalTo(message));

    }

    @And("the respond time should be less than {int}")
    public void theRespondTimeShouldBeLessThan(int time) {
        sharedData.getResponse().then().time(lessThan((long)time));


    }
}
