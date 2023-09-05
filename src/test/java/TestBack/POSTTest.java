package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class POSTTest {
    @Test
    public void PostTest_01() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "space");
        request.addProperty("job", "leader");

        given()
                .contentType("application/json")
                .body(request).post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().status()
                .log().body();
    }

    @Test
    public void PostTest_02() {
        JsonObject request = new JsonObject();
        request .addProperty("name", "SpaceR");
        request.addProperty("job", "leader");

        given().contentType("application/json")
                .body(request)
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("SpaceR"))
                .body("job", equalTo("leader"))
                .body("createdAt", containsString("2023-08-31"));
    }

    @Test
    public void PostTestFallido() {
        JsonObject request = new JsonObject();
        request.addProperty("email", "space@space");

        Response response = given().contentType("application/json").body(request).post("https://reqres.in/api/login");

        String error = response.jsonPath().getString("error");
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals(error, "Missing password");

        System.out.println(response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);
        System.out.println("EL CÓDIGO DE ERROR ES: " + response.statusCode());
        System.out.println("EL SERVICIO SE TARDO " + response.time() + " MILISEGUNDOS");
    }
}