package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class SpartanTestWithPath {
    @BeforeClass
    public void beforeclass(){
        baseURI="http://54.234.71.110:8000/";
    }


        /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        response.prettyPrint();
        //printing each key value in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());

        //save json key value
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");



        //assert one by one
        Assert.assertEquals(id,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone,3312820936l);
    }



    @Test
    public void getAllSpartanWithPath(){

        Response response  = given().accept(ContentType.JSON)
                .when().get("api/spartans");

        //get id from l=spartans with path
        int id = response.path("id[0]");
        System.out.println(id);


        //geet last names on the list
        String firstname = response.path("name[-1]");
        System.out.println(firstname);

        //get all the names with path
        List<String> names = response.path("name");
        for (String namess : names) {
            System.out.println(namess);
        }




    }
}
