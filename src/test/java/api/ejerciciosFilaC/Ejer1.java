package api.ejerciciosFilaC;

import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import api.config.Configuration;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class Ejer1 {
    private RequestInfo requestInfo;
    private Response response;
    private JSONObject body;
    private String auth;

    @BeforeEach
    public void setup() {
        requestInfo = new RequestInfo();
        body = new JSONObject();
        auth = Base64.getEncoder().encodeToString((Configuration.userRand + ":" + Configuration.password).getBytes());
    }

    @Test
    public void verifyEjercicio1() {
        createUser();
        getToken();
        createItem();
        deleteToken();
        createItemWithDeletedToken();
    }

    private void createUser() {
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

    private void getToken() {
        body.clear();
        requestInfo.setHost(Configuration.host + "api/authentication/token.json").setHeader("Authorization", "Basic " + auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail", equalTo(Configuration.userRand));

        String token = response.then().extract().path("TokenString");
        requestInfo.setHeader("Token", token);
    }

    private void createItem() {
        body.put("Content", "UPB ITEM QA TESTING");
        response = sendRequestToCreateItem();
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")));
    }

    private Response sendRequestToCreateItem() {
        requestInfo.removeHeader("Authorization").setHost(Configuration.host + "api/items.json").setBody(body.toString());
        return FactoryRequest.make("post").send(requestInfo);
    }

    private void deleteToken() {
        response = sendRequestToDeleteToken();
        response.then()
                .log().all()
                .statusCode(200)
                .body("TokenString", equalTo(requestInfo.getHeaders("Token")));
    }

    private Response sendRequestToDeleteToken() {
        return FactoryRequest.make("delete").send(requestInfo.setHost(Configuration.host + "api/authentication/token.json"));
    }

    private void createItemWithDeletedToken() {
        body.clear();
        body.put("Content", "UPB QA TESTING");
        body.put("Icon", 5);
        response = sendRequestToCreateItem();
        response.then()
                .log().all()
                .statusCode(200)
                .body("ErrorCode", equalTo(102));
    }
}

