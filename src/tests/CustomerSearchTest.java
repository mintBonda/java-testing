import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.Constants;
import utils.Waiters;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 *  Класс для проверки поиска клиента
 */
public class CustomerSearchTest extends BaseTest {
    private CustomersPage customersPage;

    private AddCustomerPage addCustomerPage;

    public void initPreconditions() {
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);

        addCustomerPage.openPage();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerTab());
        addCustomerPage.goToAddCustomerTab();
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getFirstNameField());
        addCustomerPage.addClient(Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE,
                Constants.TEST_POSTCODE_VALUE);
        Waiters.waitVisibilityOfElement(wait, addCustomerPage.getAddCustomerButton());
        addCustomerPage.clickOnAddCustomerButton();
        Waiters.waitAlertNotification(wait);
        addCustomerPage.acceptAlert();
    }

    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstName() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        Waiters.waitVisibilityOfElement(wait, customersPage.getSearchField());
        customersPage.enterSearchValue(Constants.TEST_FIRSTNAME_VALUE);

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<WebElement> customers = customersPage.getCustomersList();

        List<String> resultSearchList = Arrays.stream(customers.get(0).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_FIRSTNAME_VALUE),
                "Клиенты c указанным именем не найдены.");
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastName() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        Waiters.waitVisibilityOfElement(wait, customersPage.getSearchField());
        customersPage.enterSearchValue(Constants.TEST_LASTNAME_VALUE);

        Waiters.waitVisibilityOfElement(wait, customersPage.getSearchField());
        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(1).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_LASTNAME_VALUE),
                "Клиенты c указанной фамилией не найдены.");
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCode() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        Waiters.waitVisibilityOfElement(wait, customersPage.getSearchField());
        customersPage.enterSearchValue(Constants.TEST_POSTCODE_VALUE);

        Waiters.waitVisibilityOfElement(wait, customersPage.getSearchField());
        List<WebElement> customers = customersPage.getCustomersList();
        List<String> resultSearchList = Arrays.stream(customers.get(2).getText()
                .split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_POSTCODE_VALUE),
                "Клиенты c указанным почтовым индексом не найдены.");
    }

    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumber() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<WebElement> accountNumberList = customersPage.getCustomersAccountNumberList();
        List<String> accountNumber = Arrays.stream(accountNumberList.get(0).getText()
                .split("\\s")).toList();

        customersPage.enterSearchValue(accountNumber.get(0));

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<WebElement> foundAccountNumbers = customersPage.getCustomersAccountNumberList();
        List<String> resultSearchList = Arrays.stream(foundAccountNumbers.get(0).
                getText().split("\\s")).toList();

        Assertions.assertTrue(resultSearchList.contains(accountNumber.get(0)),
                "Клиент с введённым номером аккаунта не найден.");
    }
}