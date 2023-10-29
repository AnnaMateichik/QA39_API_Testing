package helpers;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYW5uYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjk4NjcxODg2LCJpYXQiOjE2OTgwNzE4ODZ9.6s6UAKYTbcE0dpD6tFe1iQlkMshHHls6QbSJHyQmhlk";
                                                      // use json with code charset=utf8
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8"); //constanta
    Gson gson = new Gson();

    OkHttpClient client = new OkHttpClient();
    String baseURL = "https://contactapp-telran-backend.herokuapp.com";

   String authHeader = "Authorization";



    int i = new Random().nextInt( 1000) +1000;



}
