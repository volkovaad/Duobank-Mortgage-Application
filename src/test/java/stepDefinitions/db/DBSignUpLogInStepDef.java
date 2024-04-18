package stepDefinitions.db;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import pages.SignUpPage;
import stepDefinitions.SharedData;
import stepDefinitions.ui.SignUpStepDefinitions;
import utilities.ConfigReader;
import utilities.DBUtils;

import java.sql.SQLException;
import java.util.*;

import static utilities.DBUtils.getQueryResultAsListOfLists;
import static utilities.DBUtils.getQueryResultListOfMaps;

public class DBSignUpLogInStepDef {
    SharedData sharedData;
    SignUpPage signUpPage;

  //  public DBSignUpLogInStepDef(SignUpStepDefinitions signUpStepDefinitions){
    //    this.signUpStepDefinitions = signUpStepDefinitions;
    //}

    public DBSignUpLogInStepDef(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    // Ensure unique email
    @When("a user has an existing account so the database should contain one record with email {string}")
    public void the_database_should_contain_one_record_with_username_and_email(String email) {
        String query = "SELECT * FROM tbl_user WHERE email = '" + email + "'";
        List<Map<String, Object>> result = getQueryResultListOfMaps(query);
        System.out.println(result);
        Assert.assertTrue(!result.isEmpty());
    }

    @Then("try to add another user with the same email {string} into tbl_users")
    public void try_to_insert_another_user_with_the_same_username_and_email_into_tbl_users(String email) throws SQLException {
        String query = "INSERT INTO tbl_user (email, password, first_name, last_name, phone, image, type, created_at, modified_at, zone_id, church_id, country_id, active) VALUES ('" + email + "', 'gytht6GHB7', 'John', 'Doe', '1234567890',' ',2, ' ', '  ',0,1,1,1)";

        String updatedQuery = "SELECT * FROM tbl_user WHERE email = '" + email + "'";
        DBUtils.executeUpdate(query);

        DBUtils.executeQuery(updatedQuery);
        List<List<Object>> emailResult = DBUtils.getQueryResultAsListOfLists(updatedQuery);
        System.out.println(emailResult);
        Assert.assertEquals(1, emailResult.size());

//        try {
//            DBUtils.executeUpdate(query);
//            throw new RuntimeException("Insertion succeeded but it should have failed. Duplicate email.");
//           } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to execute query.");
//        }
    }

    // Ensure unique id
    @Then("a user has an existing account with the unique id")
    public void aUserHasAnExistingAccountWithTheUniqueId() {
        String query = "SELECT id FROM tbl_user";
        List<List<Object>> userId = DBUtils.getQueryResultAsListOfLists(query);

        List<Integer> uniqueId = new ArrayList<>();
        for (List<Object> row : userId) {
            uniqueId.add((Integer) row.get(0));
        }
        System.out.println(uniqueId);

        Collections.sort(uniqueId);
        for (int i = 0; i < uniqueId.size() - 1; i++) {
            if (uniqueId.get(i).equals(uniqueId.get(i + 1))) {
                Assert.fail("Duplicate ID found: " + uniqueId.get(i));
            }
        }
    }

//Scenario: Verify the structure of the tbl_users table
    @Then("The tbl_users table should contain the following columns:")
    public void theTbl_usersTableShouldContainTheFollowingColumns(List<String>list) {
        list = new ArrayList<>(list);
       // System.out.println(list);
        String query = "SHOW COLUMNS FROM tbl_user;";
        List<String> resultList = DBUtils.getColumnData(DBUtils.getQueryResultAsListOfLists(query), 0);
     //   System.out.println(resultList);
        Assert.assertEquals(list, resultList);
    }
//  Scenario: Timestamp for user account creation in tbl_users table
     @When("A new user added into tbl_users")
     public void aNewUserAddedIntoTbl_users() throws InterruptedException {
       new SignUpStepDefinitions().theUserIsOnTheHomepage();
       new SignUpStepDefinitions().theUserClicksOnTheSignUpLink();
         Faker faker = new Faker();
         sharedData.setFirst_name_faker (faker.name().firstName());
         sharedData.setLast_name_faker (faker.name().lastName());
         sharedData.setEmail_faker(faker.internet().emailAddress());
         sharedData.setPwd_faker("GHnjgh647v");
         new SignUpPage().signUp(sharedData.getFirst_name_faker(), sharedData.getLast_name_faker(), sharedData.getEmail_faker(), sharedData.getPwd_faker());

        Thread.sleep(3000);
}
    @Then("The database should contain a record with a timestamp")
    public void theDatabaseShouldContainARecordWithATimestamp() throws SQLException {
        String query = "SELECT created_at FROM tbl_user WHERE email = '" + sharedData.getEmail_faker() + "'";
        List<List<Object>> queryResult = DBUtils.getQueryResultAsListOfLists(query);
        System.out.println(queryResult);
        Assert.assertTrue(!queryResult.isEmpty());

    }

//  Scenario: Passwords are stored as MD5 hash
    String retrievedPassword;
    @When("retrieve user's password from the tbl_users table")
public void retrieve_user_s_password_from_the_table() {
      String query = "Select password from tbl_user where id='12074' and email= \"duothechtest@gmail.com\"";
      List<List<Object>> queryPassword = getQueryResultAsListOfLists(query);
      retrievedPassword = (String) queryPassword.get(0).get(0);
      System.out.println(retrievedPassword);
}
    @Then("the password should be an MD5 hash")
    public void the_password_should_be_an_md5_hash() {
        String passwordInMD5 = DigestUtils.md5Hex(ConfigReader.getProperty("password"));
        System.out.println(passwordInMD5);
        Assert.assertEquals(retrievedPassword, passwordInMD5);
    }


    // Scenario: Verify Sign Up Information Mapping to tbl_users table
    @When("A new user signs up")
    public void a_new_user_signs_up() throws InterruptedException {
        aNewUserAddedIntoTbl_users();


    }
    @Then("The {string} table data should be mapped correctly to the following columns:")
    public void the_tbl_users_table_should_contain_a_record_with_the_user_s_details(String table, List<String> expectedColumns) {
        StringBuilder columnNames = new StringBuilder();
        expectedColumns.forEach(s -> columnNames.append(s + ","));
        columnNames.deleteCharAt(columnNames.length()-1);

        String query = String.format("SELECT %s from %s where email='%s'",
                columnNames,
                table,
                sharedData.getEmail_faker()
        );
        //System.out.println(query);
        List<Map<String, Object>> queryResult = DBUtils.getQueryResultListOfMaps(query);

        System.out.println(expectedColumns);
        System.out.println(queryResult);
        Map<String, Object> map = queryResult.get(0);

        List<String> actualColumns = new ArrayList<>(map.keySet());

        Assert.assertEquals(expectedColumns, actualColumns);
    }
}
