import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomersPage {
    public String mainPageUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    private WebDriver driver;

    public CustomersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    private WebElement customersTab;

    @FindBy(xpath = "//tr/td[1]/a[contains(text(), 'First Name')]")
    private WebElement columnHeaderFirstName;

    @FindBy(xpath =  "//input[@type='text' and @placeholder='Search Customer']")
    private WebElement searchField;

    @FindBy(xpath = "//tbody/tr[last()]/td")
    private List<WebElement> customersList;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> customersFirstNameList;

    @FindBy(xpath = "//tbody/tr/td[4]")
    private List<WebElement> customersAccountNumberList;

    public void openPage() {
        driver.get(mainPageUrl);
    }

    public void goToCustomersTab() {
        customersTab.click();
    }

    public void clickOnFirstNameColumn() {
        columnHeaderFirstName.click();
    }

    public void enterSearchValue(String searchValue) {
        searchField.sendKeys(searchValue);
    }

    public List<WebElement> getCustomersList() {
        return customersList;
    }

    public List<WebElement> getCustomersFirstNameList() {
        return customersFirstNameList;
    }

    public List<WebElement> getCustomersAccountNumberList() {
        return customersAccountNumberList;
    }
}
