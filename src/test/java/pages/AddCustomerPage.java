package pages;

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
     * Вкладка Add Customer для перехода на страницу с добавлением клиента
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

//    @Step("Перейти во вкладку Add Customer")
    public void goToAddCustomerTab() {
        addCustomerTab.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    public void clickOnAddCustomerButton() {
        addCustomerButton.click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }

    public WebElement getAddCustomerTab() {
        return addCustomerTab;
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getPostCodeField() {
        return postCodeField;
    }

    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }
}