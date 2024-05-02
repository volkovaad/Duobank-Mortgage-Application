package APItests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetUserTest {

    @BeforeClass
    public void setupClass() {
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void InvalidUserId() {

        String invalidUserId = "abc";

        given().
                header("Accept", "application/json").
                queryParam("id", invalidUserId).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(400).extract().response();
    }

    @Test
    public void NoAPIKey(){


        given().
                header("Accept", "application/json").
                when().
                log().all().
                get("/users").
                then().
                log().all().
                statusCode(401);
    }


    @Test
    public void TestGetUser() {
        given().
                header("Accept", "application/json").
                queryParam("id", 12370).
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
        @Test
        public void UserNotFound() {
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
                    queryParam("id", "1").
                    body("{}").
                    when().
                    patch("/user").
                    then().
                    log().all().
                    statusCode(404).
                    contentType("application/json");

        }



}

