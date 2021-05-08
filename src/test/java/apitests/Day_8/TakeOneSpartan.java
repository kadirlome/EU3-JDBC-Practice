package apitests.Day_8;

import apitests.Day_6_POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TakeOneSpartan {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }



    Faker faker = new Faker();

    int idNum;
    String RanName = faker.name().firstName();




    @Test(priority = 1)
    public void POSTNewSpartan() {
/*
        Spartan spartan = new Spartan();
        spartan.setName("Kadir");
        spartan.setGender("Male");
        spartan.setPhone(7325264525l);



        //    Socend Way with restassured

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"gender\":\"Male\",\n" +
                        "  \"name\":\"Kadir\",\n" +
                        "  \"phone\":7852456658\n" +
                        " }")
                .when().post("/api/spartans");

        assertEquals(response.statusCode(),201);
        response.prettyPrint();


 */

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", RanName);
        requestMap.put("gender","Male");
        requestMap.put("phone", 87596512586l);


        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("api/spartans");

        assertEquals(response.statusCode(), 201);
        response.prettyPrint();

/*
        String id = response.path("id");
        System.out.println(id);


 */

    }

    @Test(priority = 2)
    public void PUTExistingSpartan() {

        //using MAP
        Map<String, Object> requesttwo = new HashMap<>();
        requesttwo.put("name", faker.name());
        requesttwo.put("gender", "Female");
        requesttwo.put("phone", 4512512586l);

        given().accept(ContentType.JSON)
                .and().pathParam("id", 115)
                .contentType(ContentType.JSON)
                .and().body(requesttwo)
                .when().put("api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }

    @Test(priority = 3)
    public void PATCHEExistingSpartan() {
        //using MAP
        Map<String, Object> requestpatch = new HashMap<>();
        requestpatch.put("name", "kadir22");


        given().accept(ContentType.JSON)
                .and().pathParam("id", 115)
                .contentType(ContentType.JSON)
                .and().body(requestpatch)
                .when().patch("api/spartans/{id}");


    }

    @Test(priority = 4)
    public void GetThatSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 115)
                .when().get("api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        response.prettyPrint();
    }

    @Test
    public void DELETEThatSpartan() {
        given().pathParam("id", 117)
                .when().delete("api/spartans/{id}")
                .then()
                .statusCode(204).log().all();

    }


    @Test
    public void DeleteRandomAllSpartas() {


    }
}
