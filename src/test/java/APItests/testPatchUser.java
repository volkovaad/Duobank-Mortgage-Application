package APItests;

import com.github.javafaker.Faker;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.util.UUID;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class testPatchUser {


    @BeforeClass
    public void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

    }

    @Test
    public void testPatchUser()  {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "type": "2",
                 "active": "1"
                }
                """, firstName+UUID.randomUUID(), lastName, email);
        System.out.println( requestBody);


        given().
                body(requestBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "12").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("message", equalTo("User updated successfully"));

    }




    @Test
    public void testPatchUserMissingApiKey() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "type": "2",
                 "active": "1"
                }
                """, firstName, lastName, email);
        System.out.println( requestBody);


        given() .
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("id").
                when().
                patch("/user").
                then().
                log().all().
                statusCode(401);

    }
    @Test
    public void testPatchUserInvalidUserId() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "type": "2",
                 "active": "1"
                }
                """, firstName, lastName, email);
        System.out.println( requestBody);


        given().
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "12").
                body("{}").
                when().
                patch("/user").
                then().
                log().all().
                statusCode(400).
                contentType("application/json");

    }



    @Test
    public void testPatchUserNonExistentUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "type": "2",
                 "active": "1"
                }
                """, firstName, lastName, email);


        System.out.println( requestBody);

        given().
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "5"). // "5" No User
                body("{}").
                when().
                patch("/user").
                then().
                log().all().
                statusCode(404).
                contentType("application/json");

    }
    @Test
    public void testPatchUserMissingInformation()  {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "type": "2",
                 "active": "1"
                }
                """, firstName, lastName, email);
        System.out.println( requestBody);


        given().
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id").
                body("{}").
                when().
                patch("/user").
                then().
                log().all().
                statusCode(400).
                contentType("application/json");



    }
    @Test
    public void testPatchUser_Response()  {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        String requestBody = String.format("""
            {
              "first_name": "%s",
              "last_name": "%s",
              "email": "%s",
              "type": "2",
              "active": "1"
            }
            """, firstName, lastName, email);

        given().
                body(requestBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "12").
                when().
                log().all().
                patch("/user").
                then().
                log().all().
                assertThat().
                statusCode(200).
                contentType("application/json").
                time(lessThan(2000L));

    }





    }