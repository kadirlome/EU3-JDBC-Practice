package apitests.Day_1_2_3_4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Test1(){
        /*
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Nona",
              "gender": "Female",
              "phone": 7959094216
    */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        int id = response.path("id");
        String name = response.path("name");
        assertEquals(id,11);
        assertEquals(name,"Nona");

        //if you dont want path, we can assign to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String genderJson = jsonPath.getString("gender");
        long phoneJson = jsonPath.getLong("phone");

        //print the value
        System.out.println("id = " + idJson);
        System.out.println("name = " + nameJson);
        System.out.println("gender = " + genderJson);
        System.out.println("phone = " + phoneJson);

        //verification
        assertEquals(id,11);
        assertEquals(nameJson,"Nona");
        assertEquals(genderJson,"Female");
        assertEquals(phoneJson,7959094216l);
    }

}
