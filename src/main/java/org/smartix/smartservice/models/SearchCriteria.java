package org.smartix.smartservice.models;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private Long serviceId;
    private String serviceName;
    private String serviceType;
    private String serviceCategory;
    private String serviceProvider;
    private String serviceIdentifier;
    private String serviceStatus;
}
