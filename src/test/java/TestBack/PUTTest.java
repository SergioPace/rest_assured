package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class PUTTest {
    @Test
    public void PutTest_01() {
        JsonObject resquestBody = new JsonObject();
        resquestBody.addProperty("name", "SPACE");
        resquestBody.addProperty("job", "CAMBIADO POR RESTASSURED");

        given()
                .contentType("application/json")
                .body(resquestBody)
                .put("https://reqres.in/api/users/50")
                .then()
                .statusCode(200)
                .log().status();
    }

    @Test
    public void PutTest_02() {
        JsonObject resquestBody = new JsonObject();
        resquestBody.addProperty("name", "SPACE");
        resquestBody.addProperty("job", "CAMBIADO POR RESTASSURED");

        Response responseBody = given()
                .contentType("application/json")
                .body(resquestBody)
                .put("https://reqres.in/api/users/55");

        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");

        Assert.assertEquals(nombreModificado, "SPACE");
        Assert.assertEquals(trabajoModificado, "CAMBIADO POR RESTASSURED");
        Assert.assertEquals(responseBody.statusCode(), 200);
        given().contentType("application/json").body(resquestBody).put("https://reqres.in/api/users/55")
                .then().body("updatedAt", containsString("2023-09-01"));

        System.out.println("EL NOMBRE MODIFICADO ES: " + nombreModificado);
        System.out.println("EL TRABAJO MODIFICADO ES: " + trabajoModificado);
        System.out.println("EL CÓDIGO DE RESPUESTA ES: " + responseBody.statusCode());
        System.out.println("EL SERVICIO SE TARDO " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }
}
