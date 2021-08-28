package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SearchPage extends BasePage {


    private final By
            resultObtains = By.cssSelector(".generalstyles__Column-sc-1j7wv79-5 .PieceLayout__ResponsiveLayout-orsj2a-3 .PieceDetails-sc-1sszgtb-0 .PieceTitle-sc-1eg7yvt-0"),
            totalNumberResult = By.xpath("//li[@name='totalResult'] /span");

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method validates if all results contain the word 'Samsung'
     *
     * @return boolean : true if values contain the word otherwise is false.
     */
    public boolean areAllElementsContent() {
        waitUntilElementIsVisible(getElementFromList(resultObtains, 1), getWebDriver());
        return getElements(resultObtains).stream().allMatch(element -> getTextFromElement(element).contains(Text.SAMSUNG.getText()));
    }

    /**
     * Method compares if the value displayed on the page are equals to the amount showed
     *
     * @return boolean : true if values are equals otherwise is false.
     */
    public boolean areResultsEqual() {
        WebElement totalElement = getElement(totalNumberResult);
        return getElements(resultObtains).size() == Integer.parseInt(getTextFromElement(totalElement));
    }

}
