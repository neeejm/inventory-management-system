package com.neeejm.inventory.common.configs;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.privilege.PrivilegeEntity;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    // @Bean
    // public RepositoryRestConfigurer repositoryRestConfigurer() {

    //     return new RepositoryRestConfigurer() {

    //         @Override
    //         public void configureRepositoryRestConfiguration(
    //                 RepositoryRestConfiguration config,
    //                 CorsRegistry cors) {

    //             entityManager.getMetamodel().getEntities().forEach(entity -> {
    //                 config.exposeIdsFor(entity.getJavaType());
    //             });

    //             config.getExposureConfiguration()
    //                     .forDomainType(BaseEntity.class)
    //                     .disablePutForCreation();

    //             config.getExposureConfiguration()
    //                     .forDomainType(PrivilegeEntity.class)
    //                     .withItemExposure(
    //                             (metadata, httpMethods) -> httpMethods.disable(
    //                                     HttpMethod.POST,
    //                                     HttpMethod.PUT,
    //                                     HttpMethod.PATCH,
    //                                     HttpMethod.DELETE)

    //                     )
    //                     .withCollectionExposure(
    //                             (metadata, httpMethods) -> httpMethods.disable(
    //                                     HttpMethod.POST,
    //                                     HttpMethod.PUT,
    //                                     HttpMethod.PATCH,
    //                                     HttpMethod.DELETE)

    //                     );
    //         }
    //     };
    // }
}
