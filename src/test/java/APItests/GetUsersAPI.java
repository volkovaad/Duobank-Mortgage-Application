package APItests;
import static io.restassured.RestAssured.*;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.openqa.selenium.json.Json;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUsersAPI {

    @BeforeClass
    public void setupClass(){
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }


    @Test
    public void testGETUSERS() {

        String query = "SELECT COUNT(*) FROM tbl_user;";
        int expectedTotalUsers = DBUtils.getSingleIntValue(query);
        // System.out.println(expectedTotalUsers);

        int limit = 5;

        RequestSpecification requestSpecification = given().
                header("Accept", "application/json").
        //header("X-Total-Count", String.valueOf(expectedTotalUsers)).
                queryParam("limit", limit).
                //   queryParam("per-page", 3).
                 queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3");
        Response response = requestSpecification.when().
                log().all().
                get("/users");

        ValidatableResponse validatableResponse = response.then().
                log().all().
                statusCode(200).
                header("content-type", "application/json").
                body("size()", is(expectedTotalUsers)).
        //body("$", hasSize(limit)).
                time(lessThan(2000L));

        Map<String, Object> map = response.jsonPath().getMap("[0]");
        System.out.println(map);
        List<String> actualColumns = new ArrayList<>(map.keySet());
        System.out.println(actualColumns);
        List<String> expectedColumns = Arrays.asList("id", "email", "first_name", "last_name", "phone", "image", "type",
                "created_at", "modified_at", "zone_id", "church_id", "country_id", "active");
        System.out.println(expectedColumns);
        Assert.assertEquals(actualColumns, expectedColumns);
    }

        @Test
        public void testGETUSERS_negative(){

            //negative - no key (401)
        given().
                header("Accept", "application/json").
               // queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
        when().
                log().all().
                get("/users").
        then().
                log().all().
                statusCode(401);

            //negative - wrong request (405)
       given().
                    header("Accept", "application/json").
                    queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
      when().
                    log().all().
                    post("/users").
      then().
                    log().all().
                    statusCode(405);


    }


    @Test
    public void testGETUSER() {
 given().
                header("Accept", "application/json").
                queryParam("id", 12283).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
   when().
                log().all().
                get("/user").
   then().
                log().all().
                header("cache-control" ,"no-store, no-cache, must-revalidate").
                header("content-type"  ,"application/json").
                time(lessThan(2000L)).
                statusCode(200);

    }


    Object returnedvalue;
    @Test
    public void testPOSTUSER() {
 returnedvalue =    given().
                body(fakerDataBody()).
                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
            when().
                log().all().
                post("/user").
            then().
                log().all().
                header("cache-control", "no-store, no-cache, must-revalidate").
                header("content-type", "application/json").
                time(lessThan(2000L)).
                statusCode(201).extract().path("user_id");


        // check if user has been created with GET
      given().
                header("Accept", "application/json").
                queryParam("id", returnedvalue).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }

    @Test
    public void testPUTUSER() {
        //BUG - returns 500
        given().
                body(fakerDataBody()).
                header("Accept", "application/json").
                queryParam("id", 45).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                put("/user").
                then().
                log().all().
                statusCode(500).
                time(lessThan(2000L));
    }



    @Test
    public void testPATCHUSER() {

        testPOSTUSER();
        String newEmail = new Faker().internet().emailAddress();
        given().
                body( String.format("""
                        {
                          "email": "%s"
                        }
                        """, newEmail)).
                header("Accept", "application/json").
                queryParam("id", returnedvalue).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                statusCode(200).extract().path("email");
        System.out.println(newEmail);

        // check if user has been updated with GET
        given().
                header("Accept", "application/json").
                queryParam("id", returnedvalue).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200).
                body("email", equalTo(newEmail.toString().replaceAll("[{}\\ :]", "")));
    }


    @Test
    public void testDELETEUSER() {
        //create user

         testPOSTUSER();
        //delete user
        given().
                header("Accept", "application/json").
                queryParam("id", returnedvalue).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(200).
                header("cache-control" ,"no-store, no-cache, must-revalidate").
                header("content-type"  ,"application/json").
                time(lessThan(2000L));

        // check if user has been deleted with GET
        given().
                header("Accept", "application/json").
                queryParam("id", returnedvalue).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(404);

    }

    String email;
    String first;
    String last;

    public String fakerDataBody(){
        Faker faker = new Faker();

         email = faker.internet().emailAddress();
         first   = faker.name().firstName();
         last = faker.name().lastName();

        String formattedBody = String.format("""
                          {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "Hgybkj34bj!j"
                        }""", first,last,email);

        //System.out.println(formattedBody);
        return formattedBody;
    }


}
