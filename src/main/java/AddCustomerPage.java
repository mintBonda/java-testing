//import io.qameta.allure.Step;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    public String pageUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    public static final String actualAlertMessage = "Please check the details. Customer may be duplicate.";

    private WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[contains(text(), 'Add Customer')][1]")
    private WebElement addCustomerTab;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerButton;

    public void openPage() {
        driver.get(pageUrl);
    }

//    @Step("Перейти во вкладку Add Customer")
    public void goToAddCustomerTab() {
        addCustomerTab.click();
    }

    //  @Step("Заполнить поле First Name")
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    // @Step("Заполнить поле Last Name")
    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    // @Step("Заполнить поле Post Code")
    public void enterPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    //    @Step("Нажать на кнопку Add Customer")
    public void clickOnAddCustomerButton() {
        addCustomerButton.click();
    }

//    @Step("В окне оповещения нажать на кнопку Ok")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }
}
