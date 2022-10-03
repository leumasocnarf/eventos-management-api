package br.com.senaceventos.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiDocumentation {
    @Bean
    public OpenAPI createOAV3Description() {
        return new OpenAPI().info(new Info()
                .title("Eventos management API"));
    }
}
