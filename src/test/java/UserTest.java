import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("User Feature")
public class UserTest {

    @Story("register_with_new_email")
    @Test
    public void register_with_new_email(){

       // File data=new File("C:\\Users\\Administrator\\IdeaProjects\\TODO_RestAssured\\src\\test\\java\\data.json");
  //  User_POJO data=new User_POJO("todotest6@gmail.com","Test123@","Nada","mamdouh");

        User_POJO data= User_steps.generaterondomuser();
       Response response= User_API.register(data);

       User_POJO registereduser=response.body().as(User_POJO.class);

       assertThat(response.statusCode(),equalTo(201));
       assertThat(registereduser.getFirstName(),equalTo(data.getFirstName()));

    }

    @Story("register_with_used_email")
    @Test
    public void register_with_used_email(){

       // File data=new File("C:\\Users\\Administrator\\IdeaProjects\\TODO_RestAssured\\src\\test\\java\\data.json");

       // User_POJO data=new User_POJO("todotest3@gmail.com","Test123@","Nada","mamdouh");

        User_POJO data=User_steps.getregisteredemail();

        Response response= User_API.register(data);

                assertThat(response.statusCode(),equalTo(400));

                Error_POJO returnedmsg=response.body().as(Error_POJO.class);

                assertThat(returnedmsg.getMessage(),equalTo("Email is already exists in the Database"));
    }

    @Story("login_with_valid_email_and_password")
    @Test
    public void login_with_valid_email_and_password()
    {
//        HashMap<String,String> data=new HashMap<>();
//
//        data.put("email","todotest1@gmail.com");
//        data.put("password","Test123@");

     //   User_POJO data=new User_POJO("todotest4@gmail.com","Test123@");

        User_POJO data=User_steps.getregisteredemail();


        User_POJO logindata= new User_POJO(data.getEmail(), data.getPassword());

        Response response=   User_API.login(logindata);

        User_POJO registereduser=response.body().as(User_POJO.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(registereduser.getFirstName(),is(equalTo(data.getFirstName())));
        assertThat(registereduser.getAccesstoken(),not(equalTo(null)));

    }

    @Story("login_with_invalid_email_and_valid_password")
    @Test
    public void login_with_invalid_email_and_valid_password()
    {
//        HashMap<String,String> data=new HashMap<>();
//
//        data.put("email","dotest1@gmail.com");
//        data.put("password","Test123@");

//        User_POJO data=new User_POJO("dotest4@gmail.com","Test123@");
//
//        Response response=   User_API.login(data);

        User_POJO data=User_steps.generaterondomuser();
        User_POJO logindata= new User_POJO("dotest4@gmail.com", data.getPassword());

        Response response=   User_API.login(logindata);
      assertThat (response.statusCode(),equalTo(400));

        Error_POJO returnedmsg=response.body().as(Error_POJO.class);

        assertThat(returnedmsg.getMessage(),equalTo("We could not find the email in the database"));

    }

    @Story("login_with_valid_email_and_invalid_password")
    @Test
    public void login_with_valid_email_and_invalid_password()
    {
//        HashMap<String,String> data=new HashMap<>();
//
//        data.put("email","todotest1@gmail.com");
//        data.put("password","Test");

//        User_POJO data=new User_POJO("todotest4@gmail.com","Test");
//        Response response=   User_API.login(data);

        User_POJO data=User_steps.generaterondomuser();
        User_POJO logindata= new User_POJO(data.getEmail()," Test)");

        Response response=   User_API.login(logindata);

        assertThat (response.statusCode(),equalTo(400));

        Error_POJO returnedmsg=response.body().as(Error_POJO.class);

        assertThat(returnedmsg.getMessage(),equalTo("Please Fill a correct Password"));


    }

    @Story("llogin_with_invalid_email_and_password")
    @Test
    public void login_with_invalid_email_and_password()
    {
//        HashMap<String,String> data=new HashMap<>();
//
//        data.put("email","dotest1@gmail.com");
//        data.put("password","Test1");

        User_POJO data=new User_POJO("dotest4@gmail.com","Test1");

        Response response=   User_API.login(data);

        assertThat (response.statusCode(),equalTo(400));
        Error_POJO returnedmsg=response.body().as(Error_POJO.class);

        assertThat(returnedmsg.getMessage(),equalTo("Please Fill a correct Password"));

    }
}
