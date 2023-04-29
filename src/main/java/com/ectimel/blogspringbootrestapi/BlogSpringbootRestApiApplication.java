package com.ectimel.blogspringbootrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// http://localhost:8080/swagger-ui/index.html

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blog app REST API",
                description = "Spring boot blog rest api documentation.",
                version = "v1.0",
                contact = @Contact (
                        name = "Dominik",
                        email = "dtworek94@gmail.com",
                        url = "https://www.ectimel.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.ectimel.com/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Application GitHub repository.",
                url = "https://github.com/undermad/BlogApi"
        )
)
public class BlogSpringbootRestApiApplication {

    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringbootRestApiApplication.class, args);
    }

}
