package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTests {

    WebDriver webDriver;
    public BaseTests(){
        System.setProperty("webdriver.chrome.driver","/Users/marcos.billordo/Desktop/buildR13/ProjectPractice/src/main/resources/driver/chromedriver");
        webDriver= new ChromeDriver();
    }

    @BeforeTest
    public void beforeTest(){
        webDriver.manage().window().maximize();
        webDriver.get("https://www.fravega.com/");
    }

    @AfterTest
    public void afterTest(){
        webDriver.quit();
    }

}
