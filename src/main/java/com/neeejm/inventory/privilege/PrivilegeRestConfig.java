package com.neeejm.inventory.privilege;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class PrivilegeRestConfig {

    @Bean
    public RepositoryRestConfigurer privilegeRepositoryRestConfigurer() {

        return new RepositoryRestConfigurer() {

            @Override
            public void configureRepositoryRestConfiguration(
                    RepositoryRestConfiguration config,
                    CorsRegistry cors) {

                config.getExposureConfiguration()
                        .forDomainType(PrivilegeEntity.class)
                        .withItemExposure(
                                (metadata, httpMethods) -> httpMethods.disable(
                                        HttpMethod.POST,
                                        HttpMethod.PUT,
                                        HttpMethod.PATCH,
                                        HttpMethod.DELETE))
                        .withCollectionExposure(
                                (metadata, httpMethods) -> httpMethods.disable(
                                        HttpMethod.POST,
                                        HttpMethod.PUT,
                                        HttpMethod.PATCH,
                                        HttpMethod.DELETE));
            }
        };
    }
}
