package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CustomersPage;

/**
 *  Класс с методами для ожидания элементов
 */
public class Waiters {
    /**
     * Ожидать, пока не появится элемент
     */
    public static void waitVisibilityOfElement(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Ожидать, пока не будет обнаружен элемент
     */
    public static void waitVisibilityOfElementLocated(WebDriverWait wait, CustomersPage page) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.getLastRowLocator()));
    }
    /**
     * Ожидать, пока не появится окно оповещения
     */
    public static void waitAlertNotification(WebDriverWait wait) {
        wait.until(ExpectedConditions.alertIsPresent());
    }
}