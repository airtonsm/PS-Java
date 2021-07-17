package com.airton.psjava.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API Hackaton-Springboot-Chiquito")
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.example.api"))
                .paths(PathSelectors.any())
                .build().consumes(DEFAULT_PRODUCES_AND_CONSUMES).produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hackaton-SpringBoot-Chiquito Swagger")
                .description("Criando aplicação do Hackaton da Stefanini no SpringBoot")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("José Airton", "https://www.linkedin.com/in/airton-silva-285a90192/", "airtonsmorais28@gmail.com"))
                .build();
    }

}
