package com.neeejm.inventory.customer;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
        this(CustomerEntity.class);
    }

    public CustomerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CustomerEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode body = p.readValueAsTree();
        if (body.asText().equals("")) {
            return getApproriateEntity(body);
        } else {
            String[] urlParts = body.asText().split("/");
            UUID id = UUID.fromString(urlParts[urlParts.length - 1]);
            return getApproriateEntity(id);
        }
    }

    private CustomerEntity getApproriateEntity(UUID id) {
        if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        } else if (companyRepository.existsById(id)) {
            return companyRepository.findById(id).get();
        }
        return null;
    }

    private CustomerEntity getApproriateEntity(JsonNode body) {
        if (body.get("name") == null) {
            return PersonEntity.builder()
                    .email(
                        body.get("email") != null
                        ? body.get("email").asText()
                        : null
                    )
                    .primaryPhone(
                        body.get("primaryPhone") != null
                        ? body.get("primaryPhone").asText()
                        : null
                    )
                    .secondaryPhone(
                        body.get("secondaryPhone") != null
                        ? body.get("secondaryPhone").asText()
                        : null
                    )
                    // .addresses(body.findValuesAsText("addresses").stream().map(v -> {
                    // log.info("@@@: {}", v);
                    // try {
                    // return mapper.readValue(v, AddressEntity.class);
                    // } catch (JsonMappingException e) {
                    // e.printStackTrace();
                    // } catch (JsonProcessingException e) {
                    // e.printStackTrace();
                    // }
                    // return null;
                    // }).collect(Collectors.toSet()))
                    .firstName(
                        body.get("firstName") != null
                        ? body.get("firstName").asText()
                        : null
                    )
                    .lastName(
                        body.get("lastName") != null
                        ? body.get("lastName").asText()
                        : null
                    )
                    .build();
        } else {
            return null;
        }
    }

}