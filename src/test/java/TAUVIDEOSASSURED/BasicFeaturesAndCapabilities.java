package TAUVIDEOSASSURED;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;


public class BasicFeaturesAndCapabilities {


    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "California"},
                {"ca", "B2R", "Nova Scotia"}
        };
    }


    @BeforeTest
    public void beforeTest() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us/us").
                build();
        responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(HttpStatus.SC_OK).
                build();
    }

    // HERE I AM USE REUSABLE SPECIFICATION
    @Test
    public void checkStatusCode() {
        given().
                spec(requestSpecification).when().
                get("/90210").
                then().spec(responseSpecification);
    }

    // Haciendo un assert sobre el contenido que va a devolver la respuestas utilizando Content type.
    @Test
    public void checkContentType() {
        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().assertThat().contentType(ContentType.XML);
        // In this case the test will be failed because the content type is JSON.
    }

    @Test
    public void checkLogBody() {
        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().log().body();
        // In this case the test will be failed because the content type is JSON.
    }


    @Test
    public void checkingBody() {
        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().body("places[0].state", equalTo("California")).
                body("places[0].'place name'", equalTo("Beverly Hills"));
        // In this case the test will be failed because the content type is JSON.
    }

    // performing hasItem : search inside Map
    @Test(dataProvider = "zipCodesAndPlaces")
    public void checkingBodyHasItem(String countryCode, String zipCode, String location) {
        given().pathParam("countryCodePath", countryCode).
                pathParam("zipCodePath", zipCode).
                when().
                get("http://api.zippopotam.us/{countryCodePath}/{zipCodePath}").
                then().body("places[0].state", equalTo(location)).
                body("places.latitude", hasItem("34.0901"));
        // In this case the test will be failed because the content type is JSON.
    }

    // performing hasItem : search inside Map
    @Test
    public void checkingBodyNotHasItem() {
        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().body("places[0].state", equalTo("California")).
                body("places.latitude", not(hasItem("34.0901")));
        // In this case the test will be failed because the content type is JSON.
    }
}
