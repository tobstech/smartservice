package org.smartix.smartservice.resource;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.smartix.smartservice.entity.SmartService;
import org.smartix.smartservice.models.Responses;
import org.smartix.smartservice.models.SearchCriteria;
import org.smartix.smartservice.models.SmartServiceRequest;
import org.smartix.smartservice.repository.SmartServiceRepository;
import org.smartix.smartservice.service.SmartServiceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class SmartServiceResourceTest {

    @InjectMock
    SmartServiceRepository smartServiceRepository;
    @Inject
    SmartServiceResource smartServiceResource;

    private SmartService smartService;

    @BeforeEach
    void setUp() {
        smartService = new SmartService();
        smartService.setServiceId(1L);
        smartService.setServiceCategory("Home Automation");
        smartService.setServiceDescription("A smart service that enables automated control of home devices.");
        smartService.setServiceName("Home Automation System");
        smartService.setServiceIdentifier("A90000");
        smartService.setServiceProvider("SmartIx");
        smartService.setServiceStatus("ACTIVE");
        smartService.setServiceType("IoT");
    }

    @Test
    void getAllSmartServices() {
        List<SmartService> smartServiceList = new ArrayList<>();
        smartServiceList.add(smartService);
        Mockito.when(smartServiceRepository.listAll()).thenReturn(smartServiceList);
        Response response = smartServiceResource.getAllSmartServices();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<SmartService> entity = (List<SmartService>) response.getEntity();
        assertFalse(entity.isEmpty());
        assertEquals("Home Automation", entity.get(0).getServiceCategory());
        assertEquals("Home Automation System", entity.get(0).getServiceName());
        assertEquals("SmartIx", entity.get(0).getServiceProvider());
        assertEquals(1L, entity.get(0).getServiceId());
        assertEquals("IoT", entity.get(0).getServiceType());
        assertEquals("ACTIVE", entity.get(0).getServiceStatus());
        assertEquals("A90000", entity.get(0).getServiceIdentifier());
        assertEquals("A smart service that enables automated control of home devices.", entity.get(0).getServiceDescription());
    }

    @Test
    void getByServiceId() {
        Mockito.when(smartServiceRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(smartService));
        Response response = smartServiceResource.getByServiceId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        SmartService entity = (SmartService) response.getEntity();
        assertEquals("Home Automation", entity.getServiceCategory());
        assertEquals("Home Automation System", entity.getServiceName());
        assertEquals("SmartIx", entity.getServiceProvider());
        assertEquals(1L, entity.getServiceId());
        assertEquals("IoT", entity.getServiceType());
        assertEquals("ACTIVE", entity.getServiceStatus());
        assertEquals("A90000", entity.getServiceIdentifier());
        assertEquals("A smart service that enables automated control of home devices.", entity.getServiceDescription());
    }

    @Test
    void getByServiceIdNotFound() {
        Mockito.when(smartServiceRepository.findByIdOptional(1L))
                .thenReturn(Optional.empty());
        Response response = smartServiceResource.getByServiceId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void createSmartService() {
        Mockito.doNothing().when(smartServiceRepository).persist(
                ArgumentMatchers.any(SmartService.class)
        );

        Mockito.when(smartServiceRepository.isPersistent(
                ArgumentMatchers.any(SmartService.class)
        )).thenReturn(true);

        SmartServiceRequest smartServiceRequest = new SmartServiceRequest();
        smartServiceRequest.setServiceCategory("Environmental Monitoring");
        smartServiceRequest.setServiceIdentifier("A90001");
        smartServiceRequest.setServiceName("Environmental Monitoring");
        smartServiceRequest.setServiceDescription("A smart service that monitors and analyzes environmental conditions.");
        smartServiceRequest.setServiceStatus("ACTIVE");
        smartServiceRequest.setServiceProvider("EcoTech Solutions");
        smartServiceRequest.setServiceType("IoT");
        Responses responses = smartServiceResource.createSmartService(smartServiceRequest);
        assertNotNull(responses);
        assertEquals(Response.Status.CREATED.getStatusCode(),
                responses.getResponseCode());
    }

    @Test
    void createSmartServiceBadRequest() {
        Mockito.doNothing().when(smartServiceRepository).persist(
                ArgumentMatchers.any(SmartService.class)
        );
        Mockito.when(smartServiceRepository.isPersistent(
                ArgumentMatchers.any(SmartService.class)
        )).thenReturn(false);

        SmartServiceRequest smartServiceRequest = new SmartServiceRequest();
        smartServiceRequest.setServiceCategory("Environmental Monitoring");
        smartServiceRequest.setServiceIdentifier("A90001");
        smartServiceRequest.setServiceName("Environmental Monitoring");
        smartServiceRequest.setServiceDescription("A smart service that monitors and analyzes environmental conditions.");
        smartServiceRequest.setServiceStatus("ACTIVE");
        smartServiceRequest.setServiceProvider("EcoTech Solutions");
        smartServiceRequest.setServiceType("IoT");
        Responses responses = smartServiceResource.createSmartService(smartServiceRequest);
        assertNotNull(responses);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
                responses.getResponseCode());
    }

    @Test
    void deleteByServiceId() {
        Mockito.when(smartServiceRepository.deleteById(1L))
                .thenReturn(true);
        Responses responses = smartServiceResource.deleteByServiceId(1L);
        assertNotNull(responses);
        assertEquals(Response.Status.OK.getStatusCode(), responses.getResponseCode());
    }

    @Test
    void deleteByServiceIdNotFound() {
        Mockito.when(smartServiceRepository.deleteById(1L))
                .thenReturn(false);
        Responses responses = smartServiceResource.deleteByServiceId(1L);
        assertNotNull(responses);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), responses.getResponseCode());
    }

}