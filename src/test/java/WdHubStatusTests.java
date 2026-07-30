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
    }

    @Test
    public void statusSchemaTest() {
        given()
                .log().all()
                .auth().preemptive().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/status_whHub.responce_schema.json"));

    }

    @Test
    public void checkSelenoidStatusAndVersion() {
        given()
                .log().all()
                .auth().preemptive().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value.message", is("Selenoid v3.0.6 built at 2026-07-30_10:38:44AM"));

    }

    @Test
    public void shouldReturnReadyStatus() {
        given()
                .log().all()
                .auth().preemptive().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value.ready", is(true));

    }
}
