package APItests;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class UpdateUsers {

    @BeforeClass
    public void setupClass(){
        RestAssured.baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void testPUT_USER(){

        Faker faker = new Faker();

        String firstName = faker.name().firstName();

        String formattedBody =  String.format("""
                    {
                      "id": "27",
                      "first_name": "%s",
                      "last_name": "Candle",
                      "email": "susy.rath@gmail.com",
                      "created_at": "2023-04-19 14:15:16",
                      "modified_at": "",
                      "type": "2",
                      "active": "1"
                    }
                    """, firstName);

        System.out.println(formattedBody);

        given().
                body(formattedBody).
                header("accept", "application/json").
                queryParam("api_key", "c8a912d7d1c5a5a99c508f865b5eaae14a5b484f5bfe2d8f48c40e46289b47f3").
                queryParam("id", "27").
                when().
                log().all().
                put("/user").
                then().
                log().all().
                statusCode(200);




    }
























}
