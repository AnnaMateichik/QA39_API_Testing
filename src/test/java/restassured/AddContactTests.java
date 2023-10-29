package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddContactTests implements Helper {

    String endPoint ="/v1/contacts";

    @BeforeMethod
    public void  precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void addNewContactPositive(){

        ContactDTO contactDTO = ContactDTO.builder()
                .name("lara")
                .lastName("Lar")
                .email("lara" + i + "@gmail.com")
                .phone("12345678" +i)
                .address("Wegas")
                .description("description")
                .build();

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200);


    }
}
