package apitests.Day_1_2_3_4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpartanGetRequest {
    String SparUrl = "http://3.84.209.12:8000";

    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when().get(SparUrl + "/api/spartans").then()
                .assertThat().statusCode(200);
        System.out.println();
    }

    @Test
    public void test2() {
        /*
        TASK
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json;charset=UTF-8
        and json body should contain Fidole
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(SparUrl + "/api/spartans/3");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content-type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify contains "Fidole"
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    @Test
    public void test3(){

        /*
        Task
        Given no headers provided
        When Users sents GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Lenght should be 17
        And body should be "Hello from Sparta"
         */

        Response response = when().get(SparUrl+"/api/hello");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //verify if we have named date
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));



        System.out.println(response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assert.assertEquals(response.header("Content-Length"),"17");

        Assert.assertTrue(response.body().asString().contains("Hello from Sparta"));


    }

}
