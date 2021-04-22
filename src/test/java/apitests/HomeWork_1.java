package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

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
       */

        Response response = given().accept(ContentType.JSON)
                .pathParam("value", "US")
                .when().get("countries/{value");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        String id = response.path("country_id");
        assertEquals(id,"US");
        String name = response.path("Country_name");
        assertEquals(name,"United States of America");
        String regionId = response.path("Region_id");

        /*Hamcrest
        given().accept(ContentType.JSON)
                .pathParam("value", "US")
                .when().get("countries/{value")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().body("country_id",equalTo("US"),
                "Country_name",equalTo("United States of America"));
         */
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
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");



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

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{region_id:3}")
                .when().get("countries/{q}");

        assertEquals(response.statusCode(),200);
        List<Integer> regionsId = response.path("items.regions_id");

        for (int regionsid : regionsId) {
            System.out.println(regionsid);
            assertEquals(regionsid,3);
        }

        boolean hasmore = response.path("hasMore");
        assertEquals(hasmore,"false");


        List<String> countryName = response.path("Country_name");
        assertEquals(countryName,"Australia,China,India,Japan,Malaysia,Singapore");


    }

}
