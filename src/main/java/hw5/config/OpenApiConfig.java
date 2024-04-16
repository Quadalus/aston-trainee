package hw5.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Simple user chat",
                description = "User chat", version = "1.0.0",
                contact = @Contact(
                        name = "Alexander Bikkulov"
                )
        )
)
public class OpenApiConfig {
}
