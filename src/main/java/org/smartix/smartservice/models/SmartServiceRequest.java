package org.smartix.smartservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartServiceRequest {
    @Schema(required = true)
    private String serviceName; // Name or title of the IoT smart service.
    @Schema(required = true)
    private String serviceType; //Type of IoT Service
    @Schema(required = true)
    private String serviceCategory; //Category of IoT Service
    @Schema(required = true)
    private String serviceDescription; // Description of the IoT smart service and its purpose.
    @Schema(required = true)
    private String serviceProvider; // Organization providing the IoT smart service.
    @Schema(required = true)
    private String serviceIdentifier; // Identifier of the IoT smart service.
    @Schema(required = true)
    private String serviceStatus; //Current status of the IoT smart service (e.g., active, inactive, in development).

}
