package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GetAllContactTests implements Helper {

    String endPoint ="/v1/contacts";

    @BeforeMethod
    public void  precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void getAllContactsPositive(){

         given()
                .header(authHeader, token)
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint)
                .then()
                .assertThat().statusCode(200);


    }



}
