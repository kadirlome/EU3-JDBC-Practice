package apitests.Day_5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CBTraningWithJsonPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "20201")
                .when().get("student/{id}");

        //verify status code
        assertEquals(response.statusCode(), 200);

        //assign response to jsonPath
        JsonPath jsonPath = response.jsonPath();

        //get value from jsonPath
        String firstName = jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName = jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        assertEquals(city,"Chicago");

        String  zipCode = jsonPath.getString("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,"60606");






    }
}
