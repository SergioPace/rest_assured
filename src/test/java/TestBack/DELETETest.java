package TestBack;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DELETETest {
    @Test
    public void DeleteTest_01() {
        Response responseBody = given().delete("https://reqres.in/api/users/12");
        Assert.assertEquals(responseBody.statusCode(), 204);

        System.out.println("EL CÓDIGO DE RESPUESTA ES: " + responseBody.statusCode() + " QUE ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO");
        System.out.println("EL SERVICIO SE TARDO " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }

    @Test
    public void DeleteTest_02() {
        String urlREQRES = "https://reqres.in/api/";
        String pathUser = "users/";
        String deleteUser ="55";

        Response responseBody = given().delete(urlREQRES + pathUser + deleteUser);

        Assert.assertEquals(responseBody.statusCode(), 204);
        System.out.println("EL CÓDIGO DE RESPUESTA " + responseBody.statusCode() + " NOS ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO: " + deleteUser);
        System.out.println("EL SERVICIO SE TARDO " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");

    }
}
