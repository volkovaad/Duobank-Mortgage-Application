package APItests;
import com.github.javafaker.Faker;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignInPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteUsers {

    @BeforeClass
    public void setupClass(){
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    // Create a user
    @Test
    public void testDELETE_USER(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(200);

    }

    @Test
    public void testDELETE_USERnoAPIKey(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "#$@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


       given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(401).extract().response();

    }

    @Test
    public void testDELETE_USER_CheckHeader_ValidID(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(200).
                header("Content-type", equalTo("application/json"));



    }

    @Test
    public void testDELETE_USER_MissingID(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(400);




    }

    @Test
    public void testDELETE_USER_InvalidID(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L#$K";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("userid");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(400);




    }

    @Test
    public void testDELETE_USER_ID_NotExists(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(200);
        given().
                header("Accept", "application/json").
                queryParam("id", id).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(404);


    }

    @Test
    public void testDELETE_USER_NotAccessingAccountAfterDelete(){


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();



        String formattedBody =  String.format("""
                        {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "%s"
                        }
                        """,first,last,email,password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                statusCode(201).extract().response();

        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                statusCode(200);

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        new SignInPage().signIn(email, password);

        Assert.assertNotEquals("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php", Driver.getDriver().getCurrentUrl());

        Driver.quitDriver();




    }

    @Test
    public void testDELETE_USER_ResponseTime() {


        // Create a user

        Faker faker = new Faker();


        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "@L";
        String first = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                {
                  "first_name": "%s",
                  "last_name": "%s",
                  "email": "%s",
                  "password": "%s"
                }
                """, first, last, email, password);

        System.out.println(formattedBody);


        Response response = given().
                body(formattedBody).
                header("Accept", "application/json").
                header("Content-type", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                post("/user").
                then().
                log().all().
                time(lessThan(2000L)).
                statusCode(201).extract().response();


        Integer id = response.path("user_id");


//         Extract the created user's user_id and delete the user by this id

        given().

                header("Accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", id).
                when().
                log().all().
                delete("/user").
                then().
                log().all().
                time(lessThan(2000L)).
                statusCode(200);


    }

    }
