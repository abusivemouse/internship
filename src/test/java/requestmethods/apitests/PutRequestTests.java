package requestmethods.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class PutRequestTests {
    @Test
    public void testPutTextResponse() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("args.isEmpty()", equalTo(true))
                .body("data", equalTo(requestBody))
                .body("files.isEmpty()", equalTo(true))
                .body("form.isEmpty()", equalTo(true))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers['x-request-start']", notNullValue())
                .body("headers['content-length']", equalTo(String.valueOf(requestBody.length())))
                .body("headers['x-forwarded-proto']", equalTo("http"))
                .body("headers['x-forwarded-port']", equalTo("443"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers['user-agent']", notNullValue())
                .body("headers.accept-encoding", matchesPattern("gzip\\s*,\\s*deflate"))
                .body("json", equalTo(null))
                .body("url", equalTo("http://postman-echo.com/put"));
    }
}