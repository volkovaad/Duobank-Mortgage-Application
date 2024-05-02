package stepDefinitions.Api;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import stepDefinitions.SharedData;
import utilities.ConfigReader;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;
public class GetUserStepDefs {
    SharedData sharedData;

    public GetUserStepDefs(SharedData sharedData) {
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

    @Given("a valid JWT token is provided in the {string} header")
    public void a_valid_jwt_token_is_provided_in_the_header(String headerName, String token) {


        sharedData.setJWToken(token);
    }

    @And("the JWT token is set in the header")
    public void theJWTTokenIsSetInTheHeader() {

        sharedData.getRequestSpecification().header(
                "Authorization", sharedData.getJWToken()
        );
    }


    @And("the user provides a valid mortgage ID as a query parameter")
    public void theUserProvidesAValidMortgageIDAsAQueryParameter() {
        sharedData.getRequestSpecification().queryParam("12", "valid_mortgage_id");
    }

    @When("the user sends a {string} request to the endpoint {string}")
    public void theUserSendsARequestToTheEndpoint(String string, String endpoint) {
        sharedData.setResponse(sharedData.getRequestSpecification().when().
                log().all().
                get(endpoint));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer expectedStatscode) {
        sharedData.getResponse().then().
                log().all().
                statusCode(expectedStatscode);
    }

    @And("the response {string} header should be {string}")
    public void theResponseHeaderShouldBe(String key, String value) {
        sharedData.getRequestSpecification().header(key, value);
    }

    @And("the response time should be less than {int} ms")
    public void theResponseTimeShouldBeLessThanMs(int time) {
        sharedData.getResponse().then().time(lessThan((long)time));
    }

    @And("the response contains details of the mortgage application")
    public void theResponseContainsDetailsOfTheMortgageApplication() {
        String responseBody = sharedData.getResponse().getBody().asString();
    }

    @Given("the user does not provide a valid JWT token")
    public void theUserDoesNotProvideAValidJWTToken() {

    }

    @And("the response contains an error message indicating a forbidden access")
    public void theResponseContainsAnErrorMessageIndicatingAForbiddenAccess() {
        sharedData.getResponse().then()
                .body("error", equalTo("Forbidden"))
                .body("message", equalTo("Access Denied"));
    }

    @Given("the user provides an invalid JWT token")
    public void theUserProvidesAnInvalidJWTToken() {
        String accessToken = sharedData.getResponse().path("access_token");

        System.out.println(accessToken);

        sharedData.setJWToken(accessToken);
    }

    @Given("the user provides an empty mortgage ID")
    public void theUserProvidesAnEmptyMortgageID() {
        sharedData.getRequestSpecification().queryParam("id", "");
    }

    @And("the response contains an error message indicating a bad request")
    public void theResponseContainsAnErrorMessageIndicatingABadRequest() {
        String responseBody = sharedData.getResponse().getBody().asString();
        Assert.assertTrue(responseBody.contains("Bad Request"));
    }

    @And("the request body is set to the following payload as")
    public void theRequestBodyIsSetToTheFollowingPayloadAs(String docString) {
        Faker faker = new Faker();
        sharedData.getRequestSpecification().body(

                String.format(docString,
                        faker.name().username(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.internet().password())
        );
    }

    @When("I send a {string} request to the endpoint {string}")
    public void iSendARequestToTheEndpoint(String requestType, String endpoint) {


        switch (requestType){
            case "GET" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(endpoint));
            case "POST" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().post(endpoint));
            case "PUT" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().put(endpoint));
            case "PATCH" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().patch(endpoint));
            case "DELETE" -> sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().delete(endpoint));
        }
    }

    @Then("the response log should be displayed")
    public void theResponseLogShouldBeDisplayed() {
        sharedData.getResponse().then().log().all(); 
    }

    @And("the JWT token from the response is stored")
    public void theJWTTokenFromTheResponseIsStored() {
        String accessToken = sharedData.getResponse().path("access_token");
        sharedData.setJWToken(accessToken);

    }

    @And("a non-existent mortgage ID is provided as a query parameter")
    public void aNonExistentMortgageIDIsProvidedAsAQueryParameter(String string) {
        sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(string));
    }

    @And("the user  provide a valid mortgage id {int}")
    public void theUserProvideAValidMortgageId(int arg0) {
        sharedData.getRequestSpecification().queryParam("mortgage_id", "non_existent_id");

    }
}

