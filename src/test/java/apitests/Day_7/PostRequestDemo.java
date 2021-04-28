package apitests.Day_7;

import static org.hamcrest.Matchers.*;

import apitests.Day_6_POJO.Spartan;
import io.restassured.http.ContentType;
import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequestDemo {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

/*
   Given accept type and Content type is JSON
   And request json body is :
   {
     "gender" :"Male",
     "name" :"MikeEU",
     "phone":/8877445596
    }

    When user sends POST request to '/api/spartans'
    Then status code 201
    And Content-type should be application/json
    And json payload/response should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void PostNewSpartan1() {
        //Hamcrest method
        //create  a map to keep request json body information
        Map<String, Object> requestMap = new HashMap<>();

        //adding value that we want to post
                requestMap.put("name", "MikeEu2");
                requestMap.put("gender", "Male");
                requestMap.put("phone", 8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when().post("/api/spartans").then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success",is("A Spartan is Born!"),
                        "data.name",equalTo("MikeEu2"),
                        "data.gender",equalTo("Male"),
                        "data.phone",is(8877445596l));
    }

    @Test
    public void PostNewSpartan(){

        //Optional Homeworks
        //HomeWokr 1
        //1-Createcsv file from mackaroo website , which inclides name,gender,pphone
        //2- Download csv file
        //3- using testg data provider and apache poi create data driven posting from spartan

        //HomeWork2
        //create one mackaroo api for name , gender.phone
        //Send get request to retrieve rondom info from that api
        //use those info to send post request to spartan



        Spartan spartanEu = new Spartan();
        spartanEu.setName("MikeEU3");
        spartanEu.setGender("Male");
        spartanEu.setPhone(8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartanEu)
                .when().post("/api/spartans").then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success",is("A Spartan is Born!"),
                        "data.name",equalTo("MikeEU3"),
                        "data.gender",equalTo("Male"),
                        "data.phone",is(8877445596l));
    }

}
