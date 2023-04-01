import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddCustomerTest {
    private WebDriver driver;

    private AddCustomerPage addCustomerPage;

    private CustomersPage customersPage;

    private OpenAccountPage openAccountPage;

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
        openAccountPage = new OpenAccountPage(driver);

        addCustomerPage.openPage();
    }


    @Test
    @Description("Добавление клиента")
    public void addCustomer() {
        addCustomerPage.goToAddCustomerTab();
        addCustomerPage.enterFirstName(testFirstNameValue);
        addCustomerPage.enterLastName(testLastNameValue);
        addCustomerPage.enterPostCode(testPostCodeValue);
        addCustomerPage.clickOnAddCustomerButton();
        addCustomerPage.acceptAlert();

        customersPage.goToCustomersTab();

        List<WebElement> cells = customersPage.getCustomersList();
        String firstName = cells.get(0).getText();
        String lastName = cells.get(1).getText();
        String postCode = cells.get(2).getText();

        Assertions.assertArrayEquals(
                new String[] {testFirstNameValue, testLastNameValue, testPostCodeValue},
                new String[] {firstName, lastName, postCode}, "Добавленный клиент не найден");
    }


    @Test
    @Description("Проверка сообщения об ошибке при добавлении дубликата клиента")
    public void checkAlertMessage() {
        addCustomerPage.goToAddCustomerTab();
        addCustomerPage.enterFirstName(testFirstNameValue);
        addCustomerPage.enterLastName(testLastNameValue);
        addCustomerPage.enterPostCode(testPostCodeValue);
        addCustomerPage.clickOnAddCustomerButton();
        addCustomerPage.acceptAlert();

        customersPage.goToCustomersTab();

        List<WebElement> cells = customersPage.getCustomersList();
        String firstName = cells.get(0).getText();
        String lastName = cells.get(1).getText();
        String postCode = cells.get(2).getText();

        addCustomerPage.goToAddCustomerTab();
        addCustomerPage.enterFirstName(firstName);
        addCustomerPage.enterLastName(lastName);
        addCustomerPage.enterPostCode(postCode);
        addCustomerPage.clickOnAddCustomerButton();

        String alertMessage = addCustomerPage.getAlertMessage();

        Assertions.assertEquals(AddCustomerPage.actualAlertMessage, alertMessage,
                "Сообщение об ошибке не найдено или неверно записано");

        addCustomerPage.acceptAlert();
    }


    @AfterEach
    public void quit() {
        driver.quit();
    }
}
