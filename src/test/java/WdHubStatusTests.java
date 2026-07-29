import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class WdHubStatusTests extends TestBase{

    @Test
    public void statusTest() {
        given()
                .log().all()
                .auth().preemptive().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200);
/*                .body(matchesJsonSchemaInClasspath("schemas/status.response_schema.json"))
                .body("total", is(25));*/


    }

    @Test
    public void unathorizedStatusTest() {
        given()
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
/*                .body(matchesJsonSchemaInClasspath("schemas/status.response_schema.json"))
                .body("total", is(25));*/


    }
}
