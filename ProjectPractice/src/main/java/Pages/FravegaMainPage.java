package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FravegaMainPage extends BasePage {

    private final By fravegaCampaign = By.cssSelector(".sc-jJMGnK"),
                     searchBar = By.name("keyword"),
                     submitButton = By.xpath("(//button[@type='submit'] ) [1]");


    public FravegaMainPage(WebDriver webDriver) {
        super(webDriver);
        closeCampaign();
    }

    /**
     *Method closes the fravega campaign
     * */
    private void  closeCampaign(){
        click(fravegaCampaign);
    }

    /**
     *Method writes 'Heladeras' on search bar and does a click on submit button
     * @return FrigdePage
     * */
    public FrigdePage getFridgePage(){
        sendKeys(searchBar, Text.HELADERAS.getText());
        click(submitButton);
        return new FrigdePage(getWebDriver());
    }
}
