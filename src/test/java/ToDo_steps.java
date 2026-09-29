import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class ToDo_steps {

    public static ToDo_POJO addnewitem(){

        Faker faker=new Faker();
        String item=faker.book().title();
        boolean iscompleted=false;
        return new ToDo_POJO(item,iscompleted);

    }

    public static String getid(String token) {


        ToDo_POJO toDoPojo=  addnewitem();
        Response response=ToDo_API.post(toDoPojo,token);
        return   response.body().path("_id");

    }

    public static ToDo_POJO deletetaskbyid(String token, String id){

        ToDo_POJO toDoPojo=addnewitem();
        ToDo_API.deletetask(token,id);
        return toDoPojo;
    }
}
