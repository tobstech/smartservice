package org.smartix.smartservice.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SmartServiceResourceTestIT {

    @Test
    @Order(1)
    void getAllSmartServices() {
        given()
                .when()
                .get("/smart-services")
                .then()
                .body("size()", equalTo(3))
                .body("service_id", hasItems(1,2,3))
                .body("service_name", hasItems("Smart Security System","Home Automation System","Environmental Monitoring"))
                .body("service_type", hasItem("IoT"))
                .body("service_category", hasItems("Security","Home Automation","Environmental Monitoring"))
                .body("service_description", hasItems("A smart service that provides advanced security features for homes and offices.","A smart service that enables automated control of home devices.","A smart service that monitors and analyzes environmental conditions."))
                .body("service_provider", hasItems("SecureTech Solutions","SmartIx","EcoTech Solutions"))
                .body("service_identifier", hasItems("A90003","A90000","A90001"))
                .body("service_status", hasItem("ACTIVE"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(1)
    void getByServiceId() {
    }

    @Test
    @Order(2)
    void createSmartService() {
    }

    @Test
    @Order(3)
    void deleteByServiceId() {
    }

    @Test
    @Order(1)
    void searchSmartService() {
    }

}