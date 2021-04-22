package apitests.Day_5;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class HamcrestMatchersApiTest {

    @Test
    public void OneSpartanWithHamcrest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://3.84.209.12:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15),
                "name", equalTo("Meta"),
                "gender", equalTo("Female"),
                "phone", equalTo(1938695106));
    }

    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",10423)
                .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Encoding",equalTo("gzip"))
                .and().header("Content-Length",equalTo("236"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().assertThat().body( "teachers.firstName[0]",equalTo("Alexander"),
                          "teachers.lastName[0]",equalTo("Syrup"),
                                               "teachers.gender[0]",equalTo("male"))
                .log().all();
    }

    @Test
    public void teachersWithDepartments(){
        given().accept(ContentType.JSON)
                .and().pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().body("teachers.firstName",hasItems("Alexander","Marteen"));
    }
}
