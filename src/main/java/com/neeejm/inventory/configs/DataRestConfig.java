package com.neeejm.inventory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.neeejm.inventory.models.BaseEntity;
import com.neeejm.inventory.models.Privilege;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurer() {

            @Override
            public void configureRepositoryRestConfiguration(
                    RepositoryRestConfiguration config,
                    CorsRegistry cors) {
                config.setBasePath("/api/v1");

                config.getExposureConfiguration()
                        .forDomainType(BaseEntity.class)
                        .disablePutForCreation();

                config.getExposureConfiguration()
                        .forDomainType(Privilege.class)
                        .withItemExposure(
                                (metadata, httpMethods) -> httpMethods.disable(
                                        HttpMethod.POST,
                                        HttpMethod.PUT,
                                        HttpMethod.PATCH,
                                        HttpMethod.DELETE)

                        )
                        .withCollectionExposure(
                                (metadata, httpMethods) -> httpMethods.disable(
                                        HttpMethod.POST,
                                        HttpMethod.PUT,
                                        HttpMethod.PATCH,
                                        HttpMethod.DELETE)

                        );
            }
        };
    }
}
