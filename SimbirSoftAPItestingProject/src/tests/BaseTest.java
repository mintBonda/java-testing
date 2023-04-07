import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Родительский класс для всех тестовых классов
 */
public class BaseTest {
    public static SoftAssertions softly;

    @BeforeEach
    public void setUp() {
        softly = new SoftAssertions();
    }
}