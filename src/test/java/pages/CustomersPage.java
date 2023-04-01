package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Страница с таблицей всех клиентов
 */
public class CustomersPage extends BasePage{
    public CustomersPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Вкладка Customers для перехода на страницу с таблицей клиентов
     */
    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    private WebElement customersTab;
    /**
     * Заголовок столбца First Name
     */
    @FindBy(xpath = "//tr/td[1]/a[contains(text(), 'First Name')]")
    private WebElement columnHeaderFirstName;
    /**
     * Поле для ввода поискового запроса
     */
    @FindBy(xpath =  "//input[@type='text' and @placeholder='Search Customer']")
    private WebElement searchField;
    /**
     * Список с последним добавленным клиентом
     */
    @FindBy(xpath = "//tbody/tr[last()]/td")
    private List<WebElement> customersList;
    /**
     * Список с именами клиентов
     */
    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> customersFirstNameList;
    /**
     * Список с номерами аккаунтов клиентов
     */
    @FindBy(xpath = "//tbody/tr/td[4]")
    private List<WebElement> customersAccountNumberList;
    /**
     * Последний добавленный элемент
     */
    private By lastRowLocator = By.xpath("//tbody/tr[last()]/td");

    @Step("Перейти во вкладку Customers")
    public void goToCustomersTab() {
        customersTab.click();
    }

    @Step("Нажать на название столбца First Name")
    public void clickOnFirstNameColumn() {
        columnHeaderFirstName.click();
    }

    @Step("Заполнить поле для поиска")
    public void enterSearchValue(String searchValue) {
        searchField.sendKeys(searchValue);
    }

    @Step("Получить список клиентов")
    public List<WebElement> getCustomersList() {
        return customersList;
    }

    @Step("Получить список имен клиентов")
    public List<WebElement> getCustomersFirstNameList() {
        return customersFirstNameList;
    }

    @Step("Получить список с номерами аккаунтов клиентов")
    public List<WebElement> getCustomersAccountNumberList() {
        return customersAccountNumberList;
    }

    @Step("Получить вкладку с клиентами")
    public WebElement getCustomersTab() {
        return customersTab;
    }

    @Step("Получить заголовок столбца")
    public WebElement getColumnHeaderFirstName() {
        return columnHeaderFirstName;
    }

    @Step("Получить поле поиска")
    public WebElement getSearchField() {
        return searchField;
    }

    @Step("Получить локатор последней строки таблицы")
    public By getLastRowLocator() {
        return lastRowLocator;
    }
}