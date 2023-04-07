package model.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для десериализации способностей покемона
 */
public class AbilitiesDeserializer extends JsonDeserializer<List<String>> {
    @Override
    @Step("Десериализовать способности покемона в список")
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        List<String> abilities = node.findValues("ability")
                .stream()
                .map(x -> x.get("name").asText())
                .collect(Collectors.toList());

        return abilities;
    }
}