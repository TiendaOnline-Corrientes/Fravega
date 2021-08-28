package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FrigdePage extends BasePage {

    private By containerFrigde = By.cssSelector("li .CategoriesFilter__ContainerWithChildren-cdvx6t-0:nth-child(1) li:nth-child(2) ");
    private By viewAllLink = By.name("viewAllBrands");
    private By samsungOption = By.xpath("//label[@for='filterItemsamsung']");
    private By applyButtpn = By.id("apply");

    public FrigdePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method does a click on 'Heladera'
     * */
    public void clickOnFridge() {
        click(getElementFromList(containerFrigde, 0));
    }

    /**
     * Method does a click on link "ver mas"
     * */
    public void viewAll() {
        click(viewAllLink);
    }

    /**
     * Method first does a click on samsung option and then does a click on apply button
     * @return SearchPage
     * */
    public SearchPage clickOnSamsungOption() {
        click(samsungOption);
        click(applyButtpn);
        return new SearchPage(getWebDriver());
    }
}
