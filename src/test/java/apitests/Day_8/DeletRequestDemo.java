package apitests.Day_8;

import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeletRequestDemo {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void rest1(){
        given().pathParam("id",90)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }

    @Test
    public void DeleteRandom(){

        Random rd = new Random();
        int idToDelete = rd.nextInt(100)+1;

        System.out.println("This spartan id: " + idToDelete+" will be deleted.Say good bye !!");
        given().pathParam("id",idToDelete)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }


}
