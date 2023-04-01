package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Constants;

/**
 * Страница с настройками всех страниц
 */
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage() {
        driver.get(Constants.MAIN_PAGE_URL);
    }
}