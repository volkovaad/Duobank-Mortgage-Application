package stepDefinitions.api.alina;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pojo.userInfo;
import stepDefinitions.SharedData;
import utilities.ConfigReader;
import utilities.DBUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class apiStepDefs {

SharedData sharedData;

    public apiStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    String query = "SELECT COUNT(*) FROM tbl_user;";
    int expectedTotalUsers = DBUtils.getSingleIntValue(query);

//     GET USERS
//  Scenario: Retrieve all users successfully
    @When("I send a GET request to {string} with a valid API key")
    public void i_send_a_get_request_to_with_a_valid_api_key(String value) {
        sharedData.getRequestSpecification().
                queryParam("api_key", ConfigReader.getProperty("api.key"));
        sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(value));
    }

    @And("the request {string} header is set to {string}")
    public void the_request_header_is_set_to(String key, String value) {
       sharedData.getRequestSpecification().
               header(key,value);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        sharedData.getResponse().then().log().all().statusCode(code);
    }
    @And("the response time should be less than {int} ms")
    public void the_response_time_should_be_less_than_ms(Integer time) {
        sharedData.getResponse().then().time(lessThan((long)time));
    }
    @And("the response body should include all user data")
    public void the_response_body_should_include_all_user_data() {

        sharedData.getResponse().then().body("size()", is(expectedTotalUsers));
    }
    @And("the Content-Type header should have a value of {string}")
    public void the_content_type_header_should_have_a_value_of(String string) {

        sharedData.getResponse().then().header("Content-Type", string);
    }

    @And("the response should only include relevant fields such as")
    public void the_response_should_only_include_relevant_fields_such_as(List<String> expectedColumns) {
        expectedColumns = new ArrayList<>(expectedColumns);

        Map<String, Object> map = sharedData.getResponse().jsonPath().getMap("[0]");
        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(actualColumns, expectedColumns);
    }

 //   Scenario: Invalid request method

    @When("I send a POST request to {string} with a valid API key")
    public void i_send_a_post_request_to_with_a_valid_api_key(String string) {
        sharedData.getRequestSpecification().
                queryParam("api_key", ConfigReader.getProperty("api.key"));
        sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().post(string));

    }
    @And("the response body should contain an error message {string}")
    public void the_response_body_should_contain_an_error_message(String string) {
        sharedData.getResponse().then().log().all().body("message", equalTo(string));
    }

 //   Scenario: Missing API key

    @When("I send a GET request to {string} without providing an API key")
    public void i_send_a_get_request_to_without_providing_an_api_key(String string) {
        sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(string));
    }

 //   Scenario: Pagination to limit the number of results returned
 @When("I send a GET request to {string} with a valid API key and a limit query parameter")
 public void i_send_a_get_request_to_with_a_valid_api_key_and_a_limit_query_parameter(String string) {
     sharedData.getRequestSpecification().
             queryParam("api_key", ConfigReader.getProperty("api.key")).
             queryParam("limit", 5);
     sharedData.setResponse(sharedData.getRequestSpecification().when().log().all().get(string));

 }
    @And("the number of returned users should be less than or equal to the specified limit")
    public void the_number_of_returned_users_should_be_less_than_or_equal_to_the_specified_limit() {

        sharedData.getResponse().then().body("$", hasSize(5));
    }

    @And("the X-Total-Count header should indicate the total number of users in the database")
    public void the_x_total_count_header_should_indicate_the_total_number_of_users_in_the_database() {

        sharedData.getResponse().then().header("X-Total-Count", String.valueOf(expectedTotalUsers));
    }


    @And("the request body is set to the following")
    public void theRequestBodyIsSetToTheFollowingPayload() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "7Gddjjdby!7";
        userInfo userInf = userInfo.builder()
                .first_name(faker.name().firstName())
                .last_name(faker.name().lastName())
                .email(email)
                .password(password)
                .build();

        sharedData.setEmailCreated(email);
        sharedData.setPasswordCreated(password);
        sharedData.getRequestSpecification().body(userInf);

    }


    @And("the request body is set to the following body")
    public void theRequestBodyIsSetToTheFollowingBody() {
        userInfo user = userInfo.builder()
                .email(sharedData.getEmailCreated())
                .password(sharedData.getPasswordCreated())
                .build();

        sharedData.getRequestSpecification().body(user);

    }

    @And("store user id")
    public void storeUserId() {
      sharedData.setUser_id(sharedData.getResponse().then().log().all().extract().path("user_id"));
        System.out.println(sharedData.getUser_id());
    }

    @And("query parameter has user id")
    public void queryParameterHasUserId() {
        System.out.println(sharedData.getUser_id());
        sharedData.getRequestSpecification().queryParam("id", sharedData.getUser_id());
    }

    @And("the request body is changing to the following")
    public void theRequestBodyIsChangingToTheFollowing() {
        userInfo user = userInfo.builder()
                .email("hopheylalala@hotmail.com")
                .first_name("Bob")
                .build();

        sharedData.getRequestSpecification().body(user);
    }

    @And("query parameter has user id {int}")
    public void queryParameterHasUserId(int id) {
        sharedData.getRequestSpecification().queryParam("id", id);

    }
}
