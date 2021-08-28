package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage extends Wait {

    private final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void click(By locator) {
        waitUntilElementIsVisible(locator, webDriver);
        webDriver.findElement(locator).click();
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void sendKeys(By locator, String word) {
        webDriver.findElement(locator).sendKeys(word);
    }

    protected WebElement getElementFromList(By by, int position) {
        return webDriver.findElements(by).get(position);
    }

    protected List<WebElement> getByList(By by) {
        return webDriver.findElements(by);
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected List<WebElement> getElements(By by) {
        return webDriver.findElements(by);
    }

    protected String getTextFromElement(WebElement webElement) {
        return webElement.getText();
    }

    protected WebElement getElement(By by) {
        return webDriver.findElement(by);
    }

}
