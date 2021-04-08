package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class simpleGetRequest {
    String hrurl = "http://api.cybertektraining.com/students";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrurl);
        System.out.println(response.statusCode());

    }
}
