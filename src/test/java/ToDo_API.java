import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ToDo_API {

    public static Response post(ToDo_POJO data ,String token){

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .body(data)
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .extract().response();
    }

    public static Response get(String token){

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks")
                .then().log().all()
                .extract().response();
    }

    public static Response getbyspecificid(String token,String id){

        return given().baseUri("https://qacart-todo.herokuapp.com")

                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks/"+ id)
                .then().log().all()
                .extract().response();
    }

    public static Response updatetask(String token, ToDo_POJO data,String id) {

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .body(data)
                .when().put("/api/v1/tasks/"+id)
                .then().log().all()
                .extract().response();
    }

    public static Response deletetask(String token,String id) {

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when().delete("/api/v1/tasks/"+id)
                .then().log().all()
                .extract().response();
    }


    public static Response gettaskbyidafterdeleted(String token,String id) {

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks/"+id)
                .then().log().all()
                .extract().response();
    }
}
