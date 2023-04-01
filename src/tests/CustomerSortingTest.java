import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.Constants;
import utils.Waiters;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Класс для проверки сортировки клиента
 */
public class CustomerSortingTest extends BaseTest {
    private CustomersPage customersPage;

    public void initPreconditions() {
        customersPage = new CustomersPage(driver);
        customersPage.openPage();
    }

    @Test
    @Description("Сортировка клиентов по имени в лексикографическом порядке")
    public void sortCustomerByFirstName() {
        initPreconditions();
        Waiters.waitVisibilityOfElement(wait, customersPage.getCustomersTab());
        customersPage.goToCustomersTab();

        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<String> firstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertTrue(!firstNames.isEmpty(), "Клиентов в таблице нет");

        Collections.sort(firstNames, Collections.reverseOrder());

        Waiters.waitVisibilityOfElement(wait, customersPage.getColumnHeaderFirstName());
        customersPage.clickOnFirstNameColumn();
        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<String> resultReverseSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultReverseSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");

        Collections.sort(firstNames);

        Waiters.waitVisibilityOfElement(wait, customersPage.getColumnHeaderFirstName());
        customersPage.clickOnFirstNameColumn();
        Waiters.waitVisibilityOfElementLocated(wait, customersPage);
        List<String> resultSortedFirstNames = customersPage.getCustomersFirstNameList()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(firstNames.toArray(new String[0]),
                resultSortedFirstNames.toArray(new String[0]),
                "Сортировка ошибочна");
    }
}