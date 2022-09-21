package com.neeejm.inventory.customer;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.neeejm.inventory.customer.entities.CompanyEntity;
import com.neeejm.inventory.customer.entities.CustomerEntity;
import com.neeejm.inventory.customer.entities.PersonEntity;
import com.neeejm.inventory.customer.repositories.CompanyRepository;
import com.neeejm.inventory.customer.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerDeserializer extends StdDeserializer<CustomerEntity> {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public CustomerDeserializer() {
        this(null);
    }

    public CustomerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CustomerEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode body = p.getCodec().readTree(p);
        if (body.isContainerNode()) {
            if (body.get("name").asText() == null) {
                return mapper.treeToValue(body, PersonEntity.class);
            } else {
                return mapper.treeToValue(body, CompanyEntity.class);
            }
        }
        String[] urlParts = body.asText().split("/");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> {} ", urlParts[urlParts.length - 1]);
        UUID id = UUID.fromString(urlParts[urlParts.length - 1]);
        if (personRepository.existsById(id)) {
            return personRepository.findById(id).get();
        } else if (companyRepository.existsById(id)) {
            return companyRepository.findById(id).get();
        }
        
        return null;
    }

}