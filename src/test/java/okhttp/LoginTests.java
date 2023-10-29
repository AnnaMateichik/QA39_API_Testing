package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static okhttp.RegistrationTests.JSON;

public class LoginTests implements Helper {
    String endPoint = "/v1/user/login/usernamepassword";
    //    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYW5uYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjk4NjcxODg2LCJpYXQiOjE2OTgwNzE4ODZ9.6s6UAKYTbcE0dpD6tFe1iQlkMshHHls6QbSJHyQmhlk";
                                                     // use json with code charset=utf8
//   public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");  //constanta
//    Gson gson = new Gson();
//    OkHttpClient client = new OkHttpClient();
//    String baseURL = "https://contactapp-telran-backend.herokuapp.com";
    @Test
    public void loginPositive() throws IOException {


        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("anna@mail.com")
                .password("Qq12345$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(baseURL + endPoint )
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
           String responseJson =  response.body().string();
           AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println("Response code is -----> "+ response.code());
            System.out.println(responseDTO.getToken());

            Assert.assertTrue(response.isSuccessful());
        }else{
            ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
            System.out.println("Response code is -----> " + response.code());
            System.out.println(errorDTO.getStatus() + "====" + errorDTO.getMessage() + "===" +
                    errorDTO.getError());

            Assert.assertFalse(response.isSuccessful());
        }


    }
}
