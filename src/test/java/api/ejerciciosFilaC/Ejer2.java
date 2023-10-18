package api.ejerciciosFilaC;

import api.config.Configuration;
import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.config.Configuration;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class Ejer2 {
    private RequestInfo requestInfo;
    private Response response;
    private JSONObject body;
    private String auth;

    @BeforeEach
    public void setup() {
        requestInfo = new RequestInfo();
        body = new JSONObject();
        auth = "Basic " + Base64.getEncoder().encodeToString((Configuration.user + ":" + Configuration.password).getBytes());
    }

    @Test
    public void verifyEjercicio2() {
        createUsers();
        deleteUsers();
    }

    private void createUsers() {
        for (int i = 0; i < 4; i++) {
            body.put("Email", Configuration.userRand);
            body.put("Password", Configuration.password);
            body.put("FullName", Configuration.userRand);
            requestInfo.setHost(Configuration.host + "api/user.json").setBody(body.toString());
            response = FactoryRequest.make("post").send(requestInfo);
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("Email", equalTo(body.get("Email")))
                    .body("FullName", equalTo(body.get("FullName")));
        }
    }

    private void deleteUsers() {
        for (int i = 0; i < 4; i++) {
            requestInfo.setHost(Configuration.host + "api/user.json").setHeader("Authentication", auth);
            response = FactoryRequest.make("delete").send(requestInfo);
            response.then()
                    .log().all()
                    .statusCode(200);
        }
    }
}


