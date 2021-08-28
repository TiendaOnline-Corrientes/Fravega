import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

public abstract class BeforaClassFirstVidep {



    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        // anadiendo filtros : nos permiten nos permiten capturar el request y el response antes que estos sean mandados.
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        // si queremos cargar configuraciones
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).build();
    }

}
