package org.smartix.smartservice;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Smart Services APIs",
                description = "Smart Application",
                version = "1.0.0",
                license = @License(
                        name = "MIT",
                        url = "http://localhost:9098"
                )
        ),
        tags = {
                @Tag(name = "smart-services", description = "SmartServices")
        }
)
public class SmartServiceApplication extends Application {
}
