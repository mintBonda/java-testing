package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.qameta.allure.Step;
import model.deserializers.AbilitiesDeserializer;

import java.util.List;

/**
 * Класс для представления десериализованных данных
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("abilities")
    @JsonDeserialize(using = AbilitiesDeserializer.class)
    private List<String> abilities;

    @Step("Получить имя покемона")
    public String getName() {
        return name;
    }

    @Step("Получить вес покемона")
    public Integer getWeight() {
        return weight;
    }

    @Step("Получить список способностей покемона")
    public List<String> getAbilities() {
        return abilities;
    }
}