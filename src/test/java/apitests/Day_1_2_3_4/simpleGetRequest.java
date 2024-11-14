package apitests.Day_1_2_3_4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

import static io.restassured.RestAssured.*;

public class simpleGetRequest {
    String hrurl = "http://api.cybertektraining.com/students";
    String hrurl1 = "http://3.84.209.12:1000/ords/hr/regions";

    @Test
    public void test1() {
        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println(response.statusCode());

        //print the json body
        response.jsonPath();

        //Print the Json body
        response.prettyPrint();
    }
        /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then the response status code must be 200
        and body is json format
         */

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);
        Assert.assertEquals(response.statusCode(), 200);

        System.out.println(response.contentType());

        Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");


    }

    @Test
    public void test3() {
        /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then the response status code must be 200
        and body is json format
         */

        RestAssured.given().accept(ContentType.JSON).when().get(hrurl)
                .then().assertThat().statusCode(200)
                .and().contentType("application/json");

        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");
    }

    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .when().get(hrurl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");
    }

    @Test
    public void test5(){

        // request calismadigi icin sonucu alamiyoruz : hata 404 geliyor
       Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl1+"/2");
        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(response.getContentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Americas"));

        //Second way
        /*
                given()
            .accept(ContentType.JSON)
        .when()
            .get(hrurl1 + "/2")
        .then()
            .assertThat()
            .statusCode(200)
            .and()
            .contentType(ContentType.JSON)
            .and()
            .body(containsString("Americas"));
         */


        //Hemcrest Machers ile yapma
        /*
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(hrurl1 + "/2");

        Assert.assertThat(response.getStatusCode(), equalTo(200));
        Assert.assertThat(response.getContentType(), equalTo("application/json"));
        Assert.assertThat(response.body().asString(), containsString("Americas"));

         */

    }
}
