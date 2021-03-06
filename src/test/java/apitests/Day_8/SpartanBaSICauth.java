package apitests.Day_8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpartanBaSICauth {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("http://54.198.216.176:8000/api/spartans")
                .then().log().all()
                .statusCode(200);
    }


}
