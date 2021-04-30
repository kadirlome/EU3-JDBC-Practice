package apitests.Day_1_2_3_4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.baseURI;

public class hrApiWithPath {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);

        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());

        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryName = response.path("items.country_name[-1]");
        System.out.println("secondCountryName = " + secondCountryName);

        String link2 = response.path("items.links[2].href[0]");
        System.out.println("link2 = " + link2);

        //get all countries
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions'ids are equal to 2
        List<Integer> regionIDs = response.path("items.region_id");

        for (int regionID : regionIDs) {
            System.out.println(regionID);
            assertEquals(regionID, 2);
        }
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));


    }

    //makes sure we have only IT_PROG as a job_id
    @Test
    public void itProg() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("employees");

        List<String> jobId = response.path("items.job_id");
        for (String job : jobId) {
            assertEquals(job,"IT_PROG");
        }

    }
}

