package com.br.hermes.comercial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI comercialOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Comercial API")
                        .description("API para gerenciamento comercial.")
                        .version("v1"));
    }
}
