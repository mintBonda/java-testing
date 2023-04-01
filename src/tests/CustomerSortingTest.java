import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CustomersPage;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerSortingTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        customersPage = new CustomersPage(driver);
        customersPage.openPage();
    }

    @Test
    @Description("Сортировка клиентов по имени в лексикографическом порядке")
    public void sortCustomerByFirstName() {
        wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();

        wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<String> firstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertTrue(!firstNames.isEmpty(), "Клиентов в таблице нет");

        Collections.sort(firstNames, Collections.reverseOrder());

        wait.until(ExpectedConditions.visibilityOf(customersPage.getColumnHeaderFirstName()));
        customersPage.clickOnFirstNameColumn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<String> resultReverseSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultReverseSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");

        Collections.sort(firstNames);

        wait.until(ExpectedConditions.visibilityOf(customersPage.getColumnHeaderFirstName()));
        customersPage.clickOnFirstNameColumn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<String> resultSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");
    }
}