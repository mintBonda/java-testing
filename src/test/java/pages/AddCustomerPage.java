package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @Step("Перейти во вкладку Add Customer")
    public void goToAddCustomerTab() {
        addCustomerTab.click();
    }

    @Step("Заполнить поле First Name")
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    @Step("Заполнить поле Last Name")
    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    @Step("Заполнить поле Post Code")
    public void enterPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    @Step("Нажать на кнопку Add Customer")
    public void clickOnAddCustomerButton() {
        addCustomerButton.click();
    }

    @Step("В окне оповещения нажать на кнопку Ok")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    @Step("Получить сообщение из окна оповещения")
    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }

    @Step("Получить вкладку добавления клиента")
    public WebElement getAddCustomerTab() {
        return addCustomerTab;
    }

    @Step("Получить поле ввода для имени")
    public WebElement getFirstNameField() {
        return firstNameField;
    }

    @Step("Получить поле ввода для фамилии")
    public WebElement getLastNameField() {
        return lastNameField;
    }

    @Step("Получить поле ввода для почтового индекса")
    public WebElement getPostCodeField() {
        return postCodeField;
    }

    @Step("Получить кнопку добавления клиента")
    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }
}