package requestmethods.apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class PostRequestTests {


    @Test
    public void testPostRawText() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post("/post")
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
                .body("headers['x-amzn-trace-id']", notNullValue())
                .body("headers['content-type']", containsString("text/plain"))
                .body("headers['user-agent']", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", matchesPattern("gzip\\s*,\\s*deflate"))
                .body("json", equalTo(null))
                .body("url", equalTo("http://postman-echo.com/post"));
    }

    @Test
    public void testPostFormData() {
        RestAssured.baseURI = "https://postman-echo.com";
        String foo1 = "bar1";
        String foo2 = "bar2";
        given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("foo1", foo1)
                .formParam("foo2", foo2)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args.isEmpty()", equalTo(true))
                .body("data.isEmpty()", equalTo(true))
                .body("files.isEmpty()", equalTo(true))
                .body("form.foo1", equalTo(foo1))
                .body("form.foo2", equalTo(foo2))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", startsWith("t"))
                .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=UTF-8"))
                .body("headers['user-agent']", notNullValue())
                .body("headers.accept-encoding", matchesPattern("gzip\\s*,\\s*deflate"))
                .body("json.foo1", equalTo(foo1))
                .body("json.foo2", equalTo(foo2))
                .body("url", equalTo("http://postman-echo.com/post"));

    }
}


