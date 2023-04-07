package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import model.Pokemon;
import utils.Constants;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Класс для работы с полученными JSON данными
 */
public class Steps {
    @Step("Получить десериализованные данные в виде объекта Pokemon")
    public static Pokemon getPokemon(String pokemonUrl) {
        Pokemon pokemon = given()
                .filter(new AllureRestAssured())
                .baseUri(Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get(pokemonUrl)
                .then()
                .statusCode(200)
                .extract()
                .as(Pokemon.class);

        return pokemon;
    }

    @Step("Получить десериализованные данные в виде списка объектов Pokemon")
    public static List<Pokemon> getPokemonList() {
        List<Pokemon> pokemonList = given()
                .filter(new AllureRestAssured())
                .baseUri(Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("limit", Constants.LIMIT_VALUE)
                .when()
                .get(Constants.POKEMON_PATH)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results", Pokemon.class);

        return pokemonList;
    }
}