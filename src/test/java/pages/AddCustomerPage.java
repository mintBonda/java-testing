package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;
import utils.Waiters;

/**
 * Страница для добавления клиента
 */
public class AddCustomerPage extends BasePage{
    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Вкладка Add Customer для перехода на страницу добавления клиента
     */
    @FindBy(xpath = "//button[contains(text(), 'Add Customer')][1]")
    private WebElement addCustomerTab;
    /**
     * Поле для ввода имени
     */
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;
    /**
     * Поле для ввода фамилии
     */
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;
    /**
     * Поле для ввода почтового ящика
     */
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeField;
    /**
     * Кнопка Add Customer для добавления клиента
     */
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerButton;
    /**
     * Перейти во вкладку Add Customer
     */
    public void goToAddCustomerTab() {
        addCustomerTab.click();
    }
    /**
     * Заполнить поля First Name, Last Name, Post Code
     */
    public void fillFields(String firstName, String lastName, String postCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postCodeField.sendKeys(postCode);
    }
    /**
     * Добавить клиента
     */
    public void addCustomer(WebDriverWait wait) {
        Waiters.waitVisibilityOfElement(wait, this.getFirstNameField());
        this.fillFields(Constants.TEST_FIRSTNAME_VALUE, Constants.TEST_LASTNAME_VALUE,
                Constants.TEST_POSTCODE_VALUE);
        Waiters.waitVisibilityOfElement(wait, this.getAddCustomerButton());
        this.clickOnAddCustomerButton();
    }
    /**
     * Нажать на кнопку Add Customer
     */
    public void clickOnAddCustomerButton() {
        addCustomerButton.click();
    }
    /**
     * В окне оповещения нажать на кнопку Ok
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    /**
     * Получить сообщение из окна оповещения
     */
    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }
    /**
     * Получить вкладку добавления клиента
     */
    public WebElement getAddCustomerTab() {
        return addCustomerTab;
    }
    /**
     * Получить поле ввода для имени
     */
    public WebElement getFirstNameField() {
        return firstNameField;
    }
    /**
     * Получить поле ввода для фамилии
     */
    public WebElement getLastNameField() {
        return lastNameField;
    }
    /**
     * Получить поле ввода для почтового индекса
     */
    public WebElement getPostCodeField() {
        return postCodeField;
    }
    /**
     * Получить кнопку добавления клиента
     */
    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }
}