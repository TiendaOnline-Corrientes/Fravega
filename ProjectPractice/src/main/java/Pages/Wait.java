package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Wait {

    private WebDriverWait driverWait;

    public void waitUntilElementIsVisible(By locator, WebDriver driver){
        driverWait= new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilElementIsVisible(WebElement webElement, WebDriver driver){
        driverWait= new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(webElement));
    }



}
