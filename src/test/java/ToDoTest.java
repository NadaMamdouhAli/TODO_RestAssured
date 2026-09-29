import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("ToDo Feature")

public class ToDoTest {

    @Story("add_new_todo")
    @Test
    public void add_new_todo(){

       // ToDo_POJO data=new ToDo_POJO("restassured course",false );

        String token=User_steps.getusertoken();

        ToDo_POJO data=ToDo_steps.addnewitem();

        Response response= ToDo_API.post(data,token);

        assertThat(response.statusCode(),equalTo(201)) ;

        ToDo_POJO returneddata=response.body().as(ToDo_POJO.class);
        assertThat(returneddata.getItem(),is(equalTo(data.getItem())));
        assertThat(returneddata.getIsCompleted(),is(equalTo(data.getIsCompleted())));
    }

    @Story("add_new_todo_without_iscompleted")
    @Test
    public void add_new_todo_without_iscompleted(){
        String token=User_steps.getusertoken();
        ToDo_POJO data=new ToDo_POJO("restassured course");

        Response response= ToDo_API.post(data,token);
       assertThat(response.statusCode(),equalTo(400));

       Error_POJO returnedmsg=response.body().as(Error_POJO.class);


               assertThat(returnedmsg.getMessage(),equalTo("\"isCompleted\" is required"));
    }

    @Story("get_all_tasks")
    @Test
    public void get_all_tasks(){

        String token=User_steps.getusertoken();
        Response response=  ToDo_API.get(token);
        assertThat(response.statusCode(),equalTo(200));

    }

    @Story("get_task_by_id")
    @Test
    public void get_task_by_id(){

        String token=User_steps.getusertoken();
        String id=ToDo_steps.getid(token);

        Response response=  ToDo_API.getbyspecificid(token,id);
        assertThat(response.statusCode(),equalTo(200));

    }

    @Story("update_task_by_id")
    @Test
    public void update_task_by_id(){

        String token=User_steps.getusertoken();
        String id=ToDo_steps.getid(token);
        ToDo_POJO data=new ToDo_POJO("selenium",true);

        Response response=ToDo_API.updatetask(token,data,id);

        assertThat(response.statusCode(),equalTo(200));
    }

    @Story("delete_task_by_id")
    @Test
    public void delete_task_by_id(){
        String token=User_steps.getusertoken();
        String id=ToDo_steps.getid(token);
        Response response= ToDo_API.deletetask(token,id);

        assertThat(response.statusCode(),equalTo(200));

    }

    @Story("get_task_by_id_after_deleted")
    @Test
    public void get_task_by_id_after_deleted(){
        String token=User_steps.getusertoken();
        String id=ToDo_steps.getid(token);

        ToDo_POJO toDoPojo=ToDo_steps.deletetaskbyid(token,id);
      //  Response res=ToDo_API.deletetask(token,id);

        Response response=ToDo_API.gettaskbyidafterdeleted(token,id);

        assertThat(response.statusCode(),equalTo(404));

        Error_POJO returnedmsg=response.body().as(Error_POJO.class);
                assertThat(returnedmsg.getMessage(),is(equalTo("We could not find the task in our database")));
    }

}
