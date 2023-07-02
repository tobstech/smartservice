package org.smartix.smartservice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.smartix.smartservice.entity.SmartService;
import org.smartix.smartservice.models.SearchCriteria;
import org.smartix.smartservice.models.SmartServiceRequest;

public class SmartServiceService {

    public String buildQuery(SearchCriteria searchCriteria) {

        String defaultQuery = "SELECT s FROM SmartService s";

        if(searchCriteria.getServiceIdentifier() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceIdentifier = '" + searchCriteria.getServiceIdentifier() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceIdentifier = '" + searchCriteria.getServiceIdentifier() + "'";
            }
        }

        if(searchCriteria.getServiceCategory() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceCategory = '" + searchCriteria.getServiceCategory() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceCategory = '" + searchCriteria.getServiceCategory() + "'";
            }
        }

        if(searchCriteria.getServiceStatus() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceStatus = '" + searchCriteria.getServiceStatus() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceStatus = '" + searchCriteria.getServiceStatus() + "'";
            }
        }

        if(searchCriteria.getServiceName() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceName = '" + searchCriteria.getServiceName() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceName = '" + searchCriteria.getServiceName() + "'";
            }
        }

        if(searchCriteria.getServiceProvider() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceProvider = '" + searchCriteria.getServiceProvider() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceProvider = '" + searchCriteria.getServiceProvider() + "'";
            }
        }

        if(searchCriteria.getServiceType() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceType = '" + searchCriteria.getServiceType() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceType = '" + searchCriteria.getServiceType() + "'";
            }
        }

        if(searchCriteria.getServiceId() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceId = " + searchCriteria.getServiceId();
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceId = '" + searchCriteria.getServiceId();
            }
        }

        return defaultQuery;
    }

    public SmartService buildEntity(SmartServiceRequest smartServiceRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SmartService smartService = objectMapper.convertValue(smartServiceRequest, SmartService.class);
        return smartService;
    }

}
