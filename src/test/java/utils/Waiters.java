package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CustomersPage;

/**
 *  Класс с методами для ожидания элементов
 */
public class Waiters {
    public static void waitVisibilityOfElement(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitVisibilityOfElementLocated(WebDriverWait wait, CustomersPage page) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.getLastRowLocator()));
    }

    public static void waitAlertNotification(WebDriverWait wait) {
        wait.until(ExpectedConditions.alertIsPresent());
    }
}