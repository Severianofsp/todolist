package br.com.teste.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.teste.todolist"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .title("To do List")
                .description("Api para controle de lista de fazeres")
                .contact(new Contact("Dio Discord Group","https://discord.gg/rYHBvEp3","severianofsp@gmail.com"))
                .version("1.0")
                .build();
    }
}
