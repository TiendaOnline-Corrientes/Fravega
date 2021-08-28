import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = "https://api.openbrewerydb.org";
        RestAssured.basePath = "/breweries";
        RestAssured.filters(new RequestLoggingFilter());
    }
}
