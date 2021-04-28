package apitests.Day_6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestJsonToCoolection {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    /*
    Given accept type is json
    And path param spartan id is 11
    When user sends a get request to /spartans/{id}
    Then status code is 200
    And content type is json
    and "id" : 11,
    "name" :"Nona"
    "gender" : "Female",
    "phone" : "7959094216"
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("api/spartans/{id}");

        //convert Json to Java Collections(Map)
        Map<String,Object> responseMap = response.body().as(Map.class);

        System.out.println(responseMap.get("name"));
        System.out.println(responseMap.get("id"));

        Assert.assertEquals(responseMap.get("name"),"Nona");
        Assert.assertEquals(responseMap.get("gender"),"Female");

    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");

        List<Map<String,Object>> allSpartans = response.body().as(List.class);

        System.out.println(allSpartans.get(0));

        Map<String, Object> spartans1 = allSpartans.get(1);

        System.out.println("spartans1 = " + spartans1);

        int count = 1;
        for (Map<String, Object> spartansiter : allSpartans) {
            System.out.println(count+" -spartan"+spartansiter);
            count++;
        }


    }

}
