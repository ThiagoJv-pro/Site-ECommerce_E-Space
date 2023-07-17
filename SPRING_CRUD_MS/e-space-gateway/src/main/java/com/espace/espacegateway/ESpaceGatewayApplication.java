package com.espace.espacegateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ESpaceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESpaceGatewayApplication.class, args);
    }

    @Configuration
    public static class LocalHostRouteConfig {
        private final Environment environment;

        @Autowired
        public LocalHostRouteConfig(Environment environment) {
            this.environment = environment;
        }

        @Bean
        public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {

            // Obtém o valor da propriedade "LOCAL_API" definida no arquivo application.properties
            String localApi = environment.getProperty("LOCAL_API");

            // Obtém o valor da propriedade "EXTERNAL_API" definida no arquivo application.properties
            String externalApi = environment.getProperty("EXTERNAL_API");

            return builder
                    .routes()
                    .route(r -> r.path("/api/2/find", "/api/2/find/{id}", "/api/2/new")
                            .uri(localApi) // Roteia para a URL definida em "LOCAL_API" quando o caminho corresponder
                    )
                    .route(r -> r.path("/datareturn/*", "/datareturn*")
                            .uri(externalApi) // Roteia para a URL definida em "EXTERNAL_API" quando o caminho corresponder
                    )
                    .build();
        }
    }
}
