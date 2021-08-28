import Model.Request;
import Model.RequestBuilderPatron;
import data.factory.CreateUserDataFactory;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class FirstVideo extends BeforaClassFirstVidep {

    private static final Logger logger = LogManager.getLogger(FirstVideo.class);


    @Test
    public void firstTest() {
        // el content tyoe es lo primero
        String response = given().
                when().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}").post("/login").
                then().extract().asString();
        System.out.println(response);
    }

    @Test
    public void secondTest() {
        // hamcrest matchers para ser assersiones
        given().
                when().body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}").post("/login").
                then().body("token", notNullValue()).statusCode(200);


    }

    @Test
    public void thirdTest() {
        // hamcrest matchers para ser assersiones
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().body("data[0].id", equalTo(7));

    }



    @Test
    public void thirdTestDelete() {
        // hamcrest matchers para ser assersiones
        given().
                when().delete("users/2").
                then().assertThat().statusCode(204);

    }

    @Test
    public void thirdTestPatch() {
        // hamcrest matchers para ser assersiones
        String resultPatch = given().
                when().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                patch("users/2").
                then().extract().jsonPath().getString("name");

        assertThat(resultPatch,equalTo("marcos"));
    }

    @Test
    public void thirdTestPut() {
        // hamcrest matchers para ser assersiones
        String resultPatch = given().
                when().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                put("users/2").
                then().extract().jsonPath().getString("job");

        assertThat(resultPatch,equalTo("zion resident"));
    }

    // Obtener headers,  status code , body , content type.
    @Test
    public void mixExercise(){
        Response response = given().
                                    get("/users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();
        String contentType = response.getContentType();

        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
    }


    // parseando documentos
    @Test
    public void parseExercise(){
        String response = given().
                                  get("/users?page=2").then().extract().asString();

        int pages = from(response).get("pages");
        // si lo quiero en una posicion en particular
        String thirdEmail = from(response).get("data[3].email");
        // filtrad: para ello creamos una lista de mapas (SON MAPAS PORQUE SON CLAVE VALOR)
        // primero vamos a poner el nombre del arreglo donde queremos sacar se aplica el find all
        // si se pone sin el arreglo al cual se apunta entonces machea a la respuesta en general
        List<Map> userIdGreaterThanNine = from(response).get("data.findAll {user -> user.id > 9 && user.first_name === 'Rachel'}");


    }

    // Usando un POJO : Objeto plano en java
    // Pojo : con el plugin ROBOTPOJO  nos creamos la clase .
    // Ademas para que eto funcione neceitamos jackson databind de maven repository
    @Test
    public void createUserTest(){
        String response = given().
                when().body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}").post("/users").
                then().extract().asString();
        // aqui el objeto POJO que creamos en la clase user con el JSON DE LA RESPUESTA LO AGARRAMOS Y UTILIZAMOS
        User user = from(response).getObject("", User.class);
        System.out.println("the name is " + user.getName());
    }

    //Ahora vamos a pasar un POJO como respuesta
    // aca creamos el modelo
    @Test
    public void modifiedRequestResponse(){
        Request request = new Request();
        request.setJob("baker");
        request.setName("marcos");

        // builder pattern
        Request request1 = RequestBuilderPatron.
                setUser().
                setJob("carpitero").
                setName("jdjdjd").
                build();
        User user = given().
                when().body(request1).post("/users").
                then().extract().as(User.class);

        System.out.println("the name is " + user.getName());
    }

    @Test
    public void usingFakerPlugin(){


        // builder pattern
        Request userMissing = CreateUserDataFactory.missingAllInformation();
        Request userNull = CreateUserDataFactory.userWithNullValues();
        Request userCorrect = CreateUserDataFactory.userWithCorrectValues();
        System.out.println(userNull.getName());
        System.out.println(userCorrect.getName());
        System.out.println(userCorrect.getJob());
        User user = given().
                when().body(userCorrect).post("/users").
                then().extract().as(User.class);


    }

}
