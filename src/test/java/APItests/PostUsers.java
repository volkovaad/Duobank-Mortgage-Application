package APItests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PostUsers {

    @BeforeClass
    public void setupClass(){
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";    }


    @Test
    public void testPostUser() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "Asdf12#fdaas"
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
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
    public void invalidPassword() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "%s",
                          "password": "asdf12#fdaas"
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }

    @Test
    public void invalidEmail() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "%s",
                          "last_name": "%s",
                          "email": "Bechtelar",
                          "password": "Asdf12#fdaas"
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }

    @Test
    public void missingFields() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "",
                          "last_name": "",
                          "email": "",
                          "password": ""
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }


    @Test
    public void invalidFirstName() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "l",
                          "last_name": "%s",
                          "email": "carmina.purdy@gmail.com",
                          "password": "Asdf12#fdaas"
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }
    @Test
    public void invalidLastName() {

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String first   = faker.name().firstName();
        String last = faker.name().lastName();


        String formattedBody = String.format("""
                          {
                          "first_name": "%s",
                          "last_name": "l",
                          "email": "carmina.purdy@gmail.com",
                          "password": "Asdf12#fdaas"
                        }""", first,last,email);

        Object path =    given().
                body(formattedBody).
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
                queryParam("id", path).
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                when().
                log().all().
                get("/user").
                then().
                log().all().
                statusCode(200);
    }





}
