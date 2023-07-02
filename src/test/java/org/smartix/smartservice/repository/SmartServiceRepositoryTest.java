package org.smartix.smartservice.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.smartix.smartservice.entity.SmartService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SmartServiceRepositoryTest {

    @Inject
    SmartServiceRepository smartServiceRepository;

    @Test
    void findBySearchNotFound() {
        String defaultQuery = "SELECT s FROM SmartService s WHERE s.serviceIdentifier = 'A9000' AND s.serviceName = 'Home Automation Syste'";
        List<SmartService> smartServices =  smartServiceRepository.findBySearch(defaultQuery);
        assertNotNull(smartServices);
        assertTrue(smartServices.isEmpty());
    }


}