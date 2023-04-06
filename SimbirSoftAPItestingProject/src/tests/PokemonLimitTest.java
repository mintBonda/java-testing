import io.qameta.allure.Description;
import model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.Steps;
import utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для проверки ограниченного списка покемонов
 */
public class PokemonLimitTest {
    @Test
    @Description("Проверка ограниченного списка покемонов")
    public void checkPokemonLimitList() {
        List<Pokemon> pokemonList = Steps.getPokemonList();

        List<String> names = pokemonList
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        Assertions.assertTrue(pokemonList.size() == Constants.LIMIT_VALUE
                , "Ограничение списка не соответствует заданному значению" );
        Assertions.assertTrue(names.stream().allMatch(x -> !x.isEmpty())
                , "У некоторого покемона нет имени");
    }
}