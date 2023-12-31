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

public class RegistrationTests implements Helper {
    String endPoint = "/v1/user/registration/usernamepassword";

    //token = eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYW5uYTIzNzZAbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5ODc1ODM3NywiaWF0IjoxNjk4MTU4Mzc3fQ.oF8IIo8o3z7mwxmX8cYuKLYH33o-HLWVGnGf939wKi0

    @Test
    public void registrationPositive() throws IOException {

//        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("anna"+i+"@mail.com") // don't get 400 if email without "@"
                .password("Qq12345$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(baseURL + endPoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

//        if(response.isSuccessful()){
//            String responseJson =  response.body().string();
//            AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
//            System.out.println("Response code is -----> "+ response.code());
//            System.out.println(responseDTO.getToken());

            Assert.assertTrue(response.isSuccessful());
//        }else{
//            ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
//            System.out.println("Response code is -----> " + response.code());
//            System.out.println(errorDTO.getStatus() + "======" + errorDTO.getMessage() + "===" +
//                    errorDTO.getError());
//
//            Assert.assertFalse(response.isSuccessful());
//        }


    }
    @Test
    public void registrationNegativeWrongEmail() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("anna"+i+"mail.com") // don't get 400 if email without "@"
                .password("Qq12345$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(baseURL + endPoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
        System.out.println("Response code is -----> " + response.code());
        System.out.println(errorDTO.getStatus() + "====" + errorDTO.getMessage() + "===" +
                    errorDTO.getError());

            Assert.assertTrue(!response.isSuccessful());
    }
}
