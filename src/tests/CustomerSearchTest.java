import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.Constants;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerSearchTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    private CustomersPage customersPage;

    private AddCustomerPage addCustomerPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);

        addCustomerPage.openPage();
//        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerTab()));
        addCustomerPage.goToAddCustomerTab();

        //   wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getFirstNameField()));
        addCustomerPage.enterFirstName(Constants.TEST_FIRSTNAME_VALUE);
        //   wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getLastNameField()));
        addCustomerPage.enterLastName(Constants.TEST_LASTNAME_VALUE);
        //    wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getPostCodeField()));
        addCustomerPage.enterPostCode(Constants.TEST_POSTCODE_VALUE);
        //    wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerButton()));
        addCustomerPage.clickOnAddCustomerButton();
        //    wait.until(ExpectedConditions.alertIsPresent());
        addCustomerPage.acceptAlert();
    }

    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstName() {
        //    wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();
        //   wait.until(ExpectedConditions.visibilityOf(customersPage.getSearchField()));
        customersPage.enterSearchValue(Constants.TEST_FIRSTNAME_VALUE);

        //   wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> customers = customersPage.getCustomersList();

        List<String> resultSearchList = Arrays.stream(customers.get(0).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_FIRSTNAME_VALUE),
                "Клиенты c указанным именем не найдены.");
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastName() {
        //   wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();
        //   wait.until(ExpectedConditions.visibilityOf(customersPage.getSearchField()));
        customersPage.enterSearchValue(Constants.TEST_LASTNAME_VALUE);

        //   wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(1).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_LASTNAME_VALUE),
                "Клиенты c указанной фамилией не найдены.");
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCode() {
        //  wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();
        //  wait.until(ExpectedConditions.visibilityOf(customersPage.getSearchField()));
        customersPage.enterSearchValue(Constants.TEST_POSTCODE_VALUE);

        //  wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(2).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_POSTCODE_VALUE),
                "Клиенты c указанным почтовым индексом не найдены.");
    }

    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumber() {
        //   wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();

        //   wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> accountNumberList = customersPage.getCustomersAccountNumberList();
        List<String> accountNumber = Arrays.stream(accountNumberList.get(0).getText()
                .split("\\s")).toList();

        customersPage.enterSearchValue(accountNumber.get(0));

        // wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
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
