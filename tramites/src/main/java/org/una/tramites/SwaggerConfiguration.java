 package org.una.tramites;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

   @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.tramites.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())

                .tags(
                         new Tag("Usuarios", "Metodos de Usuarios"),
                          new Tag("Departamentos", "Metodos de Departamentos"),
                           new Tag("Transacciones", "Metodos de Transacciones"),
                            new Tag("Permisos Otorgados", "Metodos de Permisos Otorgados a Usuarios"),
                             new Tag("Permisos", "Metodos de Permisos de Usuarios"),
                              new Tag("Archivos Relacionados", "Metodos de Archivos Relacionados"),
                               new Tag("Clientes", "Metodos Clientes"),
                                new Tag("Notas", "Metodos de Notas"),
                                 new Tag("Parametros Generales", "Metodos de Parametros Generales"),
                                  new Tag("Requisitos", "Metodos de Requisitos"),
                                   new Tag("Requisitos Presentados", "Metodos de Requisitos Presentados"),
                                    new Tag("Tramites Cambios Estados", "Metodos de Tramites Cambios Estados"),
                                     new Tag("Tramites Tipos", "Metodos de Tramites Tipos"),
                                      new Tag("Tramites Registrados", "Metodos de Tramites Registrados"),
                                       new Tag("Tramites Estados", "Metodos de Tramite Estados"),
                                        new Tag("Variaciones", "Metodos de Variaciones")
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

