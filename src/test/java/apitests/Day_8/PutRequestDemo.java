package apitests.Day_8;

import io.restassured.http.ContentType;
import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;

public class PutRequestDemo {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Test1(){
        //Create one map for the put request json body
        Map<String,Object> putRequest = new HashMap<>();
        putRequest.put("name","PutName");
        putRequest.put("gender","Female");
        putRequest.put("phone",7321526895l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .and()
                .body(putRequest).
                when()
                .put("/api/spartans/{id}")

                .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body
    }

    @Test
    public void putRequest2(){
        Map<String,Object> ListofPut = new HashMap<>();

        ListofPut.put("name","kamola");
        ListofPut.put("gender","Female");
        ListofPut.put("phone",7859652578l);
        given().log().all()
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",89)
                .and().body(ListofPut).
                when().put("/api/spartans/{id}").then().log().all()
                .assertThat().statusCode(204);
    }

    @Test
    public void PatchTest(){

        Map<String,Object> ListofPut = new HashMap<>();

        ListofPut.put("gender","Male");
        given().log().all()
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",89)
                .and().body(ListofPut).
                when().patch("/api/spartans/{id}").then().log().all()
                .assertThat().statusCode(204);

    }
}
