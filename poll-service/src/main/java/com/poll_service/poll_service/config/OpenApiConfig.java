package com.poll_service.poll_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pollServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Poll Service API")
                        .description("API for managing polls and answers")
                        .version("1.0"));
    }
}