package stepDefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.restassured.RestAssured.given;

@Data
public class SharedData {


   private String realtor;
   private String estimPrice;
   private String downP;
   private String fundAv;

   private String firstName;
   private String middleName;
   private String lastName;
   private String suffix;
   private String email;
   private String dob;
   private String ssn;
   private String marital;
   private String cell;
   private String home;
   private String yesCoBorrower;
   private String coBorrowersFirstName;
   private String coBorrowersMiddleName;
   private String coBorrowersLastName;
   private String coBorrowersSuffix;
   private String coBorrowersEmail;
   private String coBorrowersDob;
   private String coBorrowersSsn;
   private String coBorrowersMarital;
   private String coBorrowersCell;
   private String coBorrowersHome;
   private String password;


   private String rentCheckbox;
   private String ownCheckbox;
   private String monthlyRentalPayment;
   private String monthlyMortgagePayment;

   private String first_name_faker;
   private String last_name_faker;
   private String email_faker;
   private String pwd_faker;


   private RequestSpecification requestSpecification = given();
   private Response response;
   private String JWToken;

   private int user_id;
   private List<String> appID;

   String emailCreated;
   String passwordCreated;

}
