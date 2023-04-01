import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerSearchTest {
    private WebDriver driver;

    private CustomersPage customersPage;

    private AddCustomerPage addCustomerPage;

    public String testFirstNameValue = "standard_first_name";

    public String testLastNameValue = "standard_last_name";

    public String testPostCodeValue = "standard_post_code";


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
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);


        addCustomerPage.openPage();

        addCustomerPage.goToAddCustomerTab();

        addCustomerPage.enterFirstName(testFirstNameValue);
        addCustomerPage.enterLastName(testLastNameValue);
        addCustomerPage.enterPostCode(testPostCodeValue);
        addCustomerPage.clickOnAddCustomerButton();
        addCustomerPage.acceptAlert();
    }


    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstName() {
        customersPage.goToCustomersTab();
        customersPage.enterSearchValue(testFirstNameValue);

        List<WebElement> customers = customersPage.getCustomersList();

        List<String> resultSearchList = Arrays.stream(customers.get(0).getText()
                .split("\\s")).toList();


        Assertions.assertTrue(resultSearchList.contains(testFirstNameValue),
                "Клиенты c указанным именем не найдены.");
    }


    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastName() {
        customersPage.goToCustomersTab();
        customersPage.enterSearchValue(testLastNameValue);

        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(1).getText()
                .split("\\s")).toList();


        Assertions.assertTrue(resultSearchList.contains(testLastNameValue),
                "Клиенты c указанной фамилией не найдены.");
    }


    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCode() {
        customersPage.goToCustomersTab();
        customersPage.enterSearchValue(testPostCodeValue);

        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(2).getText()
                .split("\\s")).toList();


        Assertions.assertTrue(resultSearchList.contains(testPostCodeValue),
                "Клиенты c указанным почтовым индексом не найдены.");
    }


    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumber() {
        customersPage.goToCustomersTab();

        List<WebElement> accountNumberList = customersPage.getCustomersAccountNumberList();
        List<String> accountNumber = Arrays.stream(accountNumberList.get(0).getText()
                .split("\\s")).toList();

        customersPage.enterSearchValue(accountNumber.get(0));

        List<WebElement> foundAccountNumbers = customersPage.getCustomersAccountNumberList();
        List<String> resultSearchList = Arrays.stream(foundAccountNumbers.get(0).
                getText().split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(accountNumber.get(0)),
                "Клиент с введённым номером аккаунта не найден.");
    }


    @AfterEach
    public void quit() {
        driver.quit();
    }
}
