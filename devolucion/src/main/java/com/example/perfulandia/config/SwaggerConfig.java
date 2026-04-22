package com.example.perfulandia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Microservicio de Gestión de Devoluciones - PERFULANDIA")
            .version("v5.6")
            .description("Microservicio encargado de registrar, consultar y actualizar las devoluciones realizadas por los usuarios, incluyendo los motivos, estados y montos reembolsados, sin depender de otras entidades del sistema.")
        );
    }

}
