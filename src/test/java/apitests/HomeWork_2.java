package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdbctests.ConfigurationReader;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeWork_2 {


    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void Question1(){

    /*
SPARTAN API
Q1:
Given accept type is json
And path param id is 20
When user sends a get request to "/api/spartans/{id}"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And response header contains Date
And Transfer-Encoding is chunked
And response payload values match the following:
id is 20,
name is "Lothario",
gender is "Male",
phone is 7551551687
*/

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20 )
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        response.header("Date");

        JsonPath jsonPath = response.jsonPath();

   //     String  JsonTransfer = jsonPath.getString("Transfer-Encoding");
    //    assertEquals(JsonTransfer,"chunked");

        int jsonId = jsonPath.getInt("id");
        System.out.println("jsonId = " + jsonId);
        assertEquals(jsonId,20);

        String name = jsonPath.getString("name");
        System.out.println("name = " + name);
        assertEquals(name,"Lothario");

        String gender = jsonPath.getString("gender");
        System.out.println("gender = " + gender);
        assertEquals(gender,"Male");

        int phone = jsonPath.getInt("phone");
        System.out.println("phone = " + phone);
        assertEquals(phone,"7551551687");


    }





 /*
Q2:
Given accept type is json
And query param gender = Female
And queary param nameContains = r
When user sends a get request to "/api/spartans/search"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And all genders are Female
And all names contains r
And size is 20
And totalPages is 1
And sorted is false
     */
}
