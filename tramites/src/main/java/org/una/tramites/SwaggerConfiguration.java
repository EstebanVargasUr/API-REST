 package org.una.tramites;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.tramites.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Seguridad", "Metodos de Seguridad"),
                         new Tag("Usuarios", "Entidad de Usuarios"),
                          new Tag("Usuario por Id", "Obtiene una lista con el usuario por medio del id"),
                           new Tag("Buscar usuario por cédula", "Obtiene una lista con el usuario por medio de la cédula"),
                            new Tag("Buscar usuario por nombre", "Obtiene una lista con el usuario por medio del nombre"),
                             new Tag("Crear Usuario", "Crear nuevo registro de usuario"),
                               new Tag("Modificar Usuario", "Modifica el usuario por id")
                               
                );

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Trámites Municipales",
                "Rest API sobre Trámites Municipales.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}

