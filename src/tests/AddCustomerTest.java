import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.Constants;

import java.time.Duration;
import java.util.List;

public class AddCustomerTest extends BaseTest{
    private AddCustomerPage addCustomerPage;

    private CustomersPage customersPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);

        addCustomerPage.openPage();
    }

    @Test
    @Description("Добавление клиента")
    public void addCustomerTest() {
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerTab()));
        addCustomerPage.goToAddCustomerTab();
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getFirstNameField()));
        addCustomerPage.enterFirstName(Constants.TEST_FIRSTNAME_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getLastNameField()));
        addCustomerPage.enterLastName(Constants.TEST_LASTNAME_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getPostCodeField()));
        addCustomerPage.enterPostCode(Constants.TEST_POSTCODE_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerButton()));
        addCustomerPage.clickOnAddCustomerButton();
        wait.until(ExpectedConditions.alertIsPresent());
        addCustomerPage.acceptAlert();

        wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();

        wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> customersList = customersPage.getCustomersList();

        Assertions.assertArrayEquals(
                new String[] {Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE, Constants.TEST_POSTCODE_VALUE},
                new String[] {customersList.get(0).getText(), customersList.get(1).getText(), customersList.get(2).getText()},
                "Добавленный клиент не найден");
    }

    @Test
    @Description("Проверка сообщения об ошибке при добавлении дубликата клиента")
    public void checkAlertMessage() {
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerTab()));
        addCustomerPage.goToAddCustomerTab();
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getFirstNameField()));
        addCustomerPage.enterFirstName(Constants.TEST_FIRSTNAME_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getLastNameField()));
        addCustomerPage.enterLastName(Constants.TEST_LASTNAME_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getPostCodeField()));
        addCustomerPage.enterPostCode(Constants.TEST_POSTCODE_VALUE);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerButton()));
        addCustomerPage.clickOnAddCustomerButton();
        wait.until(ExpectedConditions.alertIsPresent());
        addCustomerPage.acceptAlert();

        wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));
        customersPage.goToCustomersTab();

        wait.until(ExpectedConditions.visibilityOfElementLocated(customersPage.getLastRowLocator()));
        List<WebElement> customersList = customersPage.getCustomersList();
        String firstName = customersList.get(0).getText();
        String lastName = customersList.get(1).getText();
        String postCode = customersList.get(2).getText();

        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerTab()));
        addCustomerPage.goToAddCustomerTab();
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getFirstNameField()));
        addCustomerPage.enterFirstName(firstName);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getLastNameField()));
        addCustomerPage.enterLastName(lastName);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getPostCodeField()));
        addCustomerPage.enterPostCode(postCode);
        wait.until(ExpectedConditions.visibilityOf(addCustomerPage.getAddCustomerButton()));
        addCustomerPage.clickOnAddCustomerButton();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = addCustomerPage.getAlertMessage();

        Assertions.assertEquals(Constants.ACTUAL_ALERT_MESSAGE, alertMessage,
                "Сообщение об ошибке не найдено или неверно записано");

        addCustomerPage.acceptAlert();
    }
}