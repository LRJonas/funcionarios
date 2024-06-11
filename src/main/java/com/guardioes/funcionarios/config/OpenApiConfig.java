package com.guardioes.funcionarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI OpenApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Funcionários API")
                        .description("API para cadastro de funcionários")
                        .version("v1"));
    }
}
