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
import utils.Waiters;

import java.time.Duration;
import java.util.List;

/**
 *  Класс для проверки добавления клиента
 */
public class AddCustomerTest extends BaseTest{
    private AddCustomerPage addCustomerPage;

    private CustomersPage customersPage;

    public void initPreconditions() {
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);
        addCustomerPage.openPage();
    }

    @Test
    @Description("Добавление клиента")
    public void addCustomerTest() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerTab());
        addCustomerPage.goToAddCustomerTab();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getFirstNameField());
        addCustomerPage.addClient(Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE,
                Constants.TEST_POSTCODE_VALUE);
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerButton());
        addCustomerPage.clickOnAddCustomerButton();
        Waiters.waitAlertNotification(wait);
        addCustomerPage.acceptAlert();

        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        wait.until(ExpectedConditions.visibilityOf(customersPage.getCustomersTab()));

        customersPage.goToCustomersTab();

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<WebElement> customersList = customersPage.getCustomersList();

        Assertions.assertArrayEquals(
                new String[] {Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE, Constants.TEST_POSTCODE_VALUE},
                new String[] {customersList.get(0).getText(), customersList.get(1).getText(), customersList.get(2).getText()},
                "Добавленный клиент не найден");
    }

    @Test
    @Description("Проверка сообщения об ошибке при добавлении дубликата клиента")
    public void checkAlertMessage() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerTab());
        addCustomerPage.goToAddCustomerTab();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getFirstNameField());
        addCustomerPage.addClient(Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE,
                Constants.TEST_POSTCODE_VALUE);
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerButton());
        addCustomerPage.clickOnAddCustomerButton();
        Waiters.waitAlertNotification(wait);
        addCustomerPage.acceptAlert();

        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<WebElement> customersList = customersPage.getCustomersList();
        String firstName = customersList.get(0).getText();
        String lastName = customersList.get(1).getText();
        String postCode = customersList.get(2).getText();

        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerTab());
        addCustomerPage.goToAddCustomerTab();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getFirstNameField());
        addCustomerPage.addClient(Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE,
                Constants.TEST_POSTCODE_VALUE);

        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerButton());
        addCustomerPage.clickOnAddCustomerButton();

        Waiters.waitAlertNotification(wait);
        String alertMessage = addCustomerPage.getAlertMessage();

        Assertions.assertEquals(Constants.ACTUAL_ALERT_MESSAGE, alertMessage,
                "Сообщение об ошибке не найдено или неверно записано");

        addCustomerPage.acceptAlert();
    }
}