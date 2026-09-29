import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class User_steps {

    public static User_POJO generaterondomuser(){

        Faker faker=new Faker();

        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password();

        return new User_POJO(email,password,firstname,lastname);

    }

    public static User_POJO getregisteredemail(){

       User_POJO userPojo= generaterondomuser();
        User_API.register(userPojo);
        return  userPojo;

    }

    public static String getusertoken(){

        User_POJO userPojo= generaterondomuser();
        Response response=  User_API.register(userPojo);
      return   response.body().path("access_token");

    }


}
