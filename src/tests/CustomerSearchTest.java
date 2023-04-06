import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.Constants;
import utils.Waiters;

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
        addCustomerPage.addCustomer(wait);
        Waiters.waitAlertNotification(wait);
        addCustomerPage.acceptAlert();
    }

    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstNameTest() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        List<String> resultSearchList = customersPage.searchCustomerByValue(wait, Constants.TEST_FIRSTNAME_VALUE, 0);
        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_FIRSTNAME_VALUE),
                "Клиенты c указанным именем не найдены.");
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastNameTest() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        List<String> resultSearchList = customersPage.searchCustomerByValue(wait, Constants.TEST_LASTNAME_VALUE, 1);
        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_LASTNAME_VALUE),
                "Клиенты c указанной фамилией не найдены.");
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCodeTest() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();
        List<String> resultSearchList = customersPage.searchCustomerByValue(wait, Constants.TEST_POSTCODE_VALUE, 2);
        Assertions.assertTrue(resultSearchList.contains(Constants.TEST_POSTCODE_VALUE),
                "Клиенты c указанным почтовым индексом не найдены.");
    }

    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumberTest() {
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