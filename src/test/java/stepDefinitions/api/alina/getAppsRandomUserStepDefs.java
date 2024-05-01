package stepDefinitions.api.alina;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import stepDefinitions.SharedData;
import utilities.DBUtils;

import java.util.List;

import static org.hamcrest.Matchers.empty;

public class getAppsRandomUserStepDefs {
    SharedData sharedData;

    public getAppsRandomUserStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Then("all applications should be returned")
    public void all_applications_should_be_returned() {
        String query = "select count(*) from tbl_mortagage";
        int applications = DBUtils.getSingleIntValue(query);
        System.out.println(applications);

        List<Object> mortagageApplications = sharedData.getResponse().jsonPath().getList("mortagage_applications");
        System.out.println(mortagageApplications.size());
        Assert.assertEquals(applications, mortagageApplications.size());
    }


    @And("the user id is {int}")
    public void theUserIdIs(int userID) {
        Object userId = sharedData.getResponse().then().log().all().extract().path("user_id");
        System.out.println(userId);
    }

    @And("empty list should be returned")
    public void emptyListShouldBeReturned() {
        sharedData.getResponse().then().log().all().body("mortagage_applications", empty());

    }
}
