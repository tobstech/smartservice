package org.smartix.smartservice.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.smartix.smartservice.entity.SmartService;
import org.smartix.smartservice.models.Responses;
import org.smartix.smartservice.models.SearchCriteria;
import org.smartix.smartservice.models.SmartServiceRequest;
import org.smartix.smartservice.repository.SmartServiceRepository;
import org.smartix.smartservice.service.SmartServiceService;
import java.util.List;

@Path("/smart-services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Smart Service Resource", description="Smart Service REST API")
public class SmartServiceResource {

    @Inject
    SmartServiceRepository smartServiceRepository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getAllSmartServices",
            summary = "Get Smart Services",
            description = "Get all smart services from the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Returns List of Smart Services.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getAllSmartServices(){
        List<SmartService> smartServices = smartServiceRepository.listAll();
        return Response.ok(smartServices).build();
    }

    @GET
    @Path("{serviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getByServiceId",
            summary = "Get Smart Services By Service Id",
            description = "Get smart service based on the service Id"
    )
    @APIResponse(
            responseCode = "200",
            description = "Return smart service for a particular smart service based on provided serviceIdd",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getByServiceId(@PathParam("serviceId") Long serviceId){
        return smartServiceRepository.findByIdOptional(serviceId)
                .map(smartService -> Response.ok(smartService).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "createSmartService",
            summary = "Create a Smart Service",
            description = "Create a smart service to add inside the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Smart Service Creation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Responses createSmartService(
            @RequestBody(
                    description = "SmartService to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SmartServiceRequest.class))
            )
            SmartServiceRequest smartServiceRequest){
        Responses responses = new Responses();
        SmartService smartService = new SmartServiceService().buildEntity(smartServiceRequest);
        smartServiceRepository.persist(smartService);
        if (smartServiceRepository.isPersistent(smartService)){
            responses.setResponseCode(Response.Status.CREATED.getStatusCode());
            responses.setResponseMessage("Service Created Successfully!.");
            return responses;
        }
        responses.setResponseCode(Response.Status.BAD_REQUEST.getStatusCode());
        responses.setResponseMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        return responses;
    }

    @DELETE
    @Path("{serviceId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "deleteByServiceId",
            summary = "Delete an existing smart service",
            description = "Delete a smart service from the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Smart Service Delete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Smart service not valid",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Responses deleteByServiceId(@PathParam("serviceId") Long serviceId){
        Responses responses = new Responses();
        boolean deleted = smartServiceRepository.deleteById(serviceId);
        if (deleted){
            responses.setResponseCode(Response.Status.OK.getStatusCode());
            responses.setResponseMessage("Service Deleted Successfully!.");
            return responses;
        }
        responses.setResponseCode(Response.Status.NOT_FOUND.getStatusCode());
        responses.setResponseMessage(Response.Status.NOT_FOUND.getReasonPhrase());
        return responses;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "searchSmartService",
            summary = "Search for Smart Services",
            description = "Get smart service based on search criteria"
    )
    @APIResponse(
            responseCode = "200",
            description = "Search Operation completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response searchSmartService(SearchCriteria searchCriteria){
        SmartServiceService smartServiceService = new SmartServiceService();
        String formedQuery = smartServiceService.buildQuery(searchCriteria);
        List<SmartService> smartServices = smartServiceRepository
                .findBySearch(formedQuery);
        return Response.ok(smartServices).build();
    }




}
