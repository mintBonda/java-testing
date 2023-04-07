import io.qameta.allure.Description;
import model.Pokemon;
import org.junit.jupiter.api.Test;
import steps.Steps;
import utils.Constants;

/**
 * Класс для проверки отличий покемонов
 */
public class PokemonTest extends BaseTest {
    @Test
    @Description("Проверка отличий между покемонами")
    public void checkPokemonCharacteristics() {
        Pokemon rattataPokemon = Steps.getPokemon(Constants.RATTATA_PATH);
        Pokemon pidgeottoPokemon = Steps.getPokemon(Constants.PIDGEOTTO_PATH);

        softly.assertThat(rattataPokemon.getWeight()).isLessThan(pidgeottoPokemon.getWeight());
        softly.assertThat(rattataPokemon.getAbilities()).contains(Constants.ABILITY_NAME);
        softly.assertThat(pidgeottoPokemon.getAbilities()).doesNotContain(Constants.ABILITY_NAME);
    }
}