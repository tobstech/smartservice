package org.smartix.smartservice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.smartix.*;
import org.smartix.smartservice.models.Responses;
import org.smartix.smartservice.models.SearchCriteria;
import org.smartix.smartservice.repository.SmartServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class SmartServiceGrpcService implements SmartServiceGrpc {

    @Inject
    SmartServiceRepository smartServiceRepository;

    @Override
    public Uni<GetSmartServicesResponse> getAllSmartServices(Empty request) {
        List<org.smartix.smartservice.entity.SmartService> smartServices = smartServiceRepository.listAll();
        return (Uni<GetSmartServicesResponse>) smartServices;
    }

    @Override
    public Uni<SmartServiceResponse> createSmartService(SmartServiceRequest request) {
        Responses responses = new Responses();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        org.smartix.smartservice.models.SmartServiceRequest smartServiceRequestReq = objectMapper.convertValue(request, org.smartix.smartservice.models.SmartServiceRequest.class);
        org.smartix.smartservice.entity.SmartService smartService = new SmartServiceService().buildEntity(smartServiceRequestReq);
        smartServiceRepository.persist(smartService);
        if (smartServiceRepository.isPersistent(smartService)){
            return Uni.createFrom()
                    .item("")
                    .map(
                            msg -> SmartServiceResponse.newBuilder()
                                    .setResponseCode(Response.Status.CREATED.getStatusCode())
                                    .setResponseMessage("Service Created Successfully!!")
                                    .build()
                    );
        }
        return Uni.createFrom()
                .item("")
                .map(
                        msg -> SmartServiceResponse.newBuilder()
                                .setResponseCode(Response.Status.BAD_REQUEST.getStatusCode())
                                .setResponseMessage(Response.Status.BAD_REQUEST.getReasonPhrase())
                                .build()
                );
    }

    @Override
    public Uni<GetSmartServicesResponse> searchSmartService(SearchSmartServiceRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SearchCriteria searchCriteria = objectMapper.convertValue(request, SearchCriteria.class);
        String formedQuery = new SmartServiceService().buildQuery(searchCriteria);;
        List<org.smartix.smartservice.entity.SmartService> smartServices = smartServiceRepository
                .findBySearch(formedQuery);
        return (Uni<GetSmartServicesResponse>) smartServices;

    }

    @Override
    public Uni<SmartService> getSmartServiceById(SmartServiceIDRequest request) {
        org.smartix.smartservice.entity.SmartService smartServiceResponse = smartServiceRepository
                .findById(request.getServiceId());
        return (Uni<SmartService>) smartServiceResponse;
    }

    @Override
    public Uni<SmartServiceResponse> deleteSmartServiceById(SmartServiceIDRequest request) {
        smartServiceRepository.deleteById(request.getServiceId());
        return Uni.createFrom()
                .item("")
                .map(
                        msg -> SmartServiceResponse.newBuilder()
                                .setResponseCode(0)
                                .setResponseMessage("Delete Successful!")
                                .build()
                );
    }


}




