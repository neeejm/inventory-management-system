package com.neeejm.inventory.common.utils;

import java.io.InputStream;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class JsonSchemaUtil {
    
    private JsonSchemaUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static <T> Set<ValidationMessage> validate(T entity, InputStream schema) {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        JsonSchema jsonSchema = factory.getSchema(schema);
        JsonNode data = mapper.valueToTree(entity);
        return jsonSchema.validate(data);
    }
}
