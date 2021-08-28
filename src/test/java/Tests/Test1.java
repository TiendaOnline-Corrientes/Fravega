package Tests;


import Pages.FravegaMainPage;
import Pages.FrigdePage;
import Pages.SearchPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseTests {

    private final String
            errorTotalResults= "the result was not the same",
            errorMessage = "the message did not contain the Samsung word";

    @Test(description = "first exercise")
    public void test()  {
        FravegaMainPage fravegaMainPage = new FravegaMainPage(webDriver);
        FrigdePage frigdePage = fravegaMainPage.getFridgePage();
        frigdePage.clickOnFridge();
        frigdePage.viewAll();
        SearchPage searchPage = frigdePage.clickOnSamsungOption();
        Assert.assertTrue(searchPage.areAllElementsContent(),errorTotalResults);
        Assert.assertTrue(searchPage.areResultsEqual(),errorMessage);
        // the last assertions was not made because name="breadcrumb" did not exist on the page.
    }
}
