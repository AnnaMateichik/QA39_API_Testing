package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests implements Helper {

    String endPoint ="/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void  precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void registrationPositive() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("anna" + i + "@mail.com")
                .password("Qq12345$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);
    }
    @Test
    public void registrationNegativeDuplicateEmail() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("anna@mail.com")
                .password("Qq12345$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(AuthResponseDTO.class);
    }
    @Test
    public void registrationNegativeWrongEmail() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("annamail.com")
                .password("Qq12345$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(AuthResponseDTO.class);
    }
}
