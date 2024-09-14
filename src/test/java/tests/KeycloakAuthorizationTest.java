package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;


public class KeycloakAuthorizationTest {
    private static final String KEYCLOAK_URL = "http://localhost:8080/auth/realms/your-realm/protocol/openid-connect/token";
    private static final String CLIENT_ID = System.getenv("KEYCLOAK_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("KEYCLOAK_CLIENT_SECRET");
    private static final String USERNAME = System.getenv("KEYCLOAK_USERNAME");
    private static final String PASSWORD = System.getenv("KEYCLOAK_PASSWORD");

    // Метод для получения токена доступа
    private String getAccessToken() {
        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id", CLIENT_ID)
                .formParam("client_secret", CLIENT_SECRET)
                .formParam("username", USERNAME)
                .formParam("password", PASSWORD)
                .formParam("grant_type", "password")
                .post(KEYCLOAK_URL);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to obtain access token: " + response.getStatusLine());
        }

        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        return jsonResponse.getString("access_token");
    }


}
