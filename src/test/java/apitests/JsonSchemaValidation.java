package apitests;

import io.restassured.http.ContentType;
import jdbctests.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonSchemaValidation {


    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",10)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }

}
