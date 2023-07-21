package com.prueba.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.prueba.usuarios.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "API Creacion Usuario",
                "Api Rest encargada de crear un objeto del tipo Usuario, validando correo y password.",
                "1.0",
                "http://localhost:8080/usuarios/",
                new Contact("Hector Hidalgo E.", "https://www.linkedin.com/in/hector-hidalgo-espinoza90/", "h.hidalgo1990@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
