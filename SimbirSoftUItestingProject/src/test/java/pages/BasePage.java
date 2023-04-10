package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Constants;

/**
 * Родительский класс для всех страниц
 */
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Открыть главную страницу
     */
    public void openPage() {
        driver.get(Constants.MAIN_PAGE_URL);
    }
}