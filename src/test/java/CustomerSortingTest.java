import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CustomerSortingTest {
    private WebDriver driver;

    private CustomersPage customersPage;


    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        customersPage = new CustomersPage(driver);
        customersPage.openPage();
    }


    @Test
    @Description("Сортировка клиентов по имени в лексикографическом порядке")
    public void sortCustomerByFirstName() {
        customersPage.goToCustomersTab();

        List<String> firstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Collections.sort(firstNames, Collections.reverseOrder());

        customersPage.clickOnFirstNameColumn();
        List<String> resultReverseSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());


        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultReverseSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");


        Collections.sort(firstNames);

        customersPage.clickOnFirstNameColumn();
        List<String> resultSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");
    }


    @AfterEach
    public void quit() {
        driver.quit();
    }
}


