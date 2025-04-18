package br.com.fiap.locatech.locatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration 
public class OpenApiConfig {
    @Bean
    public OpenAPI locaTech(){
        return new OpenAPI()
        .info( 
            new Info().title("Locatech API")
            .description("API para gerenciamento de localização de veículos")
            .version("v0.0.1")
            .license(new License().name("apache 2.0").url("https://github.com/lcvinicius"))
        
        );
    }

}
