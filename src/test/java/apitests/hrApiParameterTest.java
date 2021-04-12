package apitests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class hrApiParameterTest {
    @BeforeClass
    public void beforeclass(){
        baseURI="hr_api_url";
    }

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .when().get("/regions");
    }
}
