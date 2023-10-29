package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByIDTests implements Helper {
//    ContactDTO contactDTO;
    String id;
    String endPoint ="/v1/contacts";
    @BeforeMethod
    public void  precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        ContactDTO contactDTO = ContactDTO.builder()
                .name("lara")
                .lastName("Lar")
                .email("lara" + i + "@gmail.com")
                .phone("12345678" +i)
                .address("Wegas")
                .description("description")
                .build();

        String message = given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .extract()
                .path("message");
        id = message.substring((message.lastIndexOf(" ") + 1) );
    }



    @Test
    public void deleteContactByIPositive(){
        given()
                .header(authHeader, token)
                .contentType(ContentType.JSON)
                .when()
                .delete(endPoint + "/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was deleted"));


    }
}
