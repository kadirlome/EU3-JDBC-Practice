package apitests.Day_5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class jsonToJavaCollection {


    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void spartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //we will convert json to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);

        System.out.println("jsonDataMap = " + jsonDataMap);
        String name = (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");
    }
}
