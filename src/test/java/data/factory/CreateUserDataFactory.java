package data.factory;

import Model.Request;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import static Model.RequestBuilderPatron.setUser;

public class CreateUserDataFactory {

    // esta clase me va a permitir retornar un objeto de varias formas.
    //java faker: datos de manera aleatoria : esta libreria ponemos en el POM.
    private static final Faker faker = new Faker();

    public static Request missingAllInformation(){
        return setUser()
                        .setName(StringUtils.EMPTY)
                        .setJob(StringUtils.EMPTY).
                         build();
    }

    public static Request userWithNullValues(){
        return setUser().setName(null).setJob(null).build();
    }

    public static Request userWithCorrectValues(){
        return setUser().
                        setJob(faker.job().field()).
                        setName(faker.name().firstName())
                        .build();
    }

}
