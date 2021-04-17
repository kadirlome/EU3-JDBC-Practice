package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeWork_1 {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Tcase1() {
       /*
        Q1:
        -Given accept type is Json
        -Path param value -US
        - When users sends request to /countries
        - Then status code is 200
        - And Content - Type is Json
        -And country_id is US
        - And Country_name is United States of America
        -And Region_id is
       */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("value", "US")
                .when().get("countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        String CountId = response.path("country_id").toString();
        String CountName = response.path("country_name").toString();
        String RegionId = response.path("region_id").toString();

        System.out.println("CountId = " + CountId);
        System.out.println("CountName = " + CountName);
        System.out.println("RegionId = " + RegionId);
    }

    @Test
    public void Tcase2() {
        /*
        Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
         */
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80")
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(jsonPath.get());

    }


    @Test
    public void Tcase3(){
          /*
Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore

     */
        /*
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{region_id:3")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        List<Integer> regionsId = response.path("items.regions_id");

        for (int regionsid : regionsId) {
            System.out.println(regionsid);
            assertEquals(regionsid,3);
        }
        boolean hasmore = response.path("false");
        JsonPath jsonPath = response.jsonPath();

      //  List<String> names = "Australia,China,India,Japan,Malaysia,Singapore";

        boolean hasmre = jsonPath.getBoolean("hasMore");
        int count =jsonPath.getInt("count");
        List<String> countNames = jsonPath.getList("Country_name");

        assertEquals(hasmore,"false");
        assertEquals(count,6);
        assertEquals(countNames,);


         */
    }

}
