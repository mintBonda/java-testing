import io.qameta.allure.Description;
import model.Pokemon;
import org.junit.jupiter.api.Test;
import steps.Steps;
import utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для проверки ограниченного списка покемонов
 */
public class PokemonLimitTest extends BaseTest {
    @Test
    @Description("Проверка ограниченного списка покемонов")
    public void checkPokemonLimitList() {
        List<Pokemon> pokemonList = Steps.getPokemonList();

        List<String> names = pokemonList
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        softly.assertThat(pokemonList.size()).isEqualTo(Constants.LIMIT_VALUE);
        softly.assertThat(names.stream().allMatch(x -> !x.isEmpty())).isTrue();
        softly.assertAll();
    }
}