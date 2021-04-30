package apitests.Day_1_2_3_4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.*;

import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeWork_2 {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Question1() {

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

        given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json"))
                .and().header("Transfer-Encoding", equalTo("chunked"))
                .and().assertThat().body("id", equalTo(20),
                "name", equalTo("Lothario"),
                "gender", equalTo("Male"),
                "phone", equalTo(7551551687l));

    }


 /*
Q2:
Given accept type is json
And query param gender = Female
And query param nameContains = r
When user sends a get request to "/api/spartans/search"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And all genders are Female
And all names contains r
And size is 17
And totalPages is 1
And sorted is false
     */

    @Test
    public void Question2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "r")
                .when().get("api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");


        JsonPath jsonPath = response.jsonPath();

        //And all genders are Female
        int count =1;
        List<String> genders = jsonPath.getList("content.gender");
        for (String gender : genders) {
            System.out.println(count++ +" "+gender);
            assertEquals(gender, "Female");
        }

        //And all names contains r
        int counter = 1;
        List<String> allnames = jsonPath.getList("content.name");
        for (String name : allnames) {
            assertTrue(name.toLowerCase().contains("r"));
            System.out.println(counter++ +" "+name);

        }
    }


}

