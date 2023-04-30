package com.ectimel.blogspringbootrestapi;

import com.ectimel.blogspringbootrestapi.entity.Role;
import com.ectimel.blogspringbootrestapi.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class BlogSpringbootRestApiApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringbootRestApiApplication.class, args);
    }

    private RoleRepository roleRepository;

    public BlogSpringbootRestApiApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        THIS CODE WILL RUN ON STARTUP OF OUR APP
//                AFTER YOU FIRST TIME DEPLOY IT ON AWS WHERE OUR DATABASE WILL BE EMPTY
//                MAKE SURE YOU COMMENT THAT CODE OUT ON NEXT DEPLOY


        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);


    }
}
