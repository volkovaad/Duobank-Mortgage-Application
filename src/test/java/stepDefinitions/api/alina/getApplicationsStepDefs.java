package stepDefinitions.api.alina;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import stepDefinitions.SharedData;
import utilities.ConfigReader;
import utilities.DBUtils;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class getApplicationsStepDefs {

    SharedData sharedData;

    public getApplicationsStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Given("the request specification is reset")
    public void the_request_specification_is_reset() {
        sharedData.setRequestSpecification(given());
    }
    @Given("the JWT token is set in the header")
    public void the_jwt_token_is_set_in_the_header() {

        sharedData.getRequestSpecification().header(
                "Authorization", sharedData.getJWToken()
        );

    }
    @Then("the response {string} header should be {string}")
    public void the_response_header_should_be(String key, String v) {
        sharedData.getResponse().then().header(key, v);


    }

    @And("must return a list of mortgage applications with the following information for each application")
    public void mustReturnAListOfMortgageApplicationsWithTheFollowingInformationForEachApplication(List<String>list) {
        list = new ArrayList<>(list);
        System.out.println(list);

        Map<String, Object> map = sharedData.getResponse().jsonPath().getMap("mortagage_applications[0]");
        List<String> actualColumns = new ArrayList<>(map.keySet());
        System.out.println(actualColumns);

        Assert.assertEquals(list, actualColumns);
    }

    @And("list of mortgage applications must be ordered by creation date")
    public void listOfMortgageApplicationsMustBeOrderedByCreationDate() {
      sharedData.getRequestSpecification().queryParam("sort", "created_on");
    }


    @And("extract creation date from database and it shouldn't be null")
    public void creationDateShouldnTBeNull() {
        String query = "select id from tbl_mortagage where b_email='" + ConfigReader.getProperty("email") + "'";
        List <List<Object>> listid = DBUtils.getqueryasasALISTwithconnection (query);
        System.out.println(listid);
        String query1 = "select created_on from tbl_mortagage where b_email='" + ConfigReader.getProperty("email") + "'";
        List <List<Object>> listData = DBUtils.getqueryasasALISTwithconnection (query1);
        System.out.println(listData);
        sharedData.getResponse().then().body("mortagage_applications.id", notNullValue());

    }

    @And("appid should be stored and list of mortgage applications must sort by creation date or appID")
    public void appidShouldBeStored() {
        sharedData.setAppID(sharedData.getResponse().then().log().all().extract().path("mortagage_applications.id"));
        System.out.println(sharedData.getAppID());
        List<String> sordtedID = new ArrayList<>(sharedData.getAppID());
        Collections.sort(sordtedID, Collections.reverseOrder());
        System.out.println(sordtedID);
        Assert.assertEquals(sordtedID, sharedData.getAppID());

    }

    @Given("the user id is provided")
    public void theUserIdIsProvided() {
        String query = "SELECT user_id FROM tbl_mortagage WHERE b_email = '" + ConfigReader.getProperty("email") + "'";
        sharedData.setUser_id(DBUtils.getSingleIntValue(query));
        sharedData.getRequestSpecification().queryParam("user_id", sharedData.getUser_id());
    }

    @Then("amount of applications should be equals the applications for this specific user")
    public void amountOfApplicationsShouldBeEqualsTheApplicationsForThisSpecificUser() {
        String query = "select count(*) from tbl_mortagage where user_id='" + sharedData.getUser_id() + "'";
        int applications = DBUtils.getSingleIntValue(query);
        System.out.println(applications);

        List<Object> mortagageApplications = sharedData.getResponse().jsonPath().getList("mortagage_applications");
        Assert.assertEquals(applications, mortagageApplications.size());


    }

}
