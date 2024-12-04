package requestmethods.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTests {

    @Test
    public void TestGetRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", startsWith("t"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.accept", equalTo("*/*"))
                .body("headers['user-agent']", notNullValue())
                .body("url", equalTo("http://postman-echo.com/get?foo1=bar1&foo2=bar2"));

    }
}