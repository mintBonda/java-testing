import io.qameta.allure.Description;
import model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.Steps;
import utils.Constants;

/**
 * Класс для проверки отличий покемонов
 */
public class PokemonTest {
    @Test
    @Description("Проверка отличий между покемонами")
    public void checkPokemonCharacteristics() {
        Pokemon rattataPokemon = Steps.getPokemon(Constants.RATTATA_PATH);
        Pokemon pidgeottoPokemon = Steps.getPokemon(Constants.PIDGEOTTO_PATH);

        Assertions.assertTrue(rattataPokemon.getWeight() < pidgeottoPokemon.getWeight(),
                "Вес покемона rattata не меньше веса покемона pidgeotto");
        Assertions.assertTrue(rattataPokemon.getAbilities().contains(Constants.ABILITY_NAME),
                "У покемона rattata нет умения побег (run-away)");
        Assertions.assertFalse(pidgeottoPokemon.getAbilities().contains(Constants.ABILITY_NAME),
                "У покемона pidgeotto есть умение побег (run-away)");
    }
}