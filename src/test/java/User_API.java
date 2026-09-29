import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class User_API {

    public static Response register(User_POJO data){

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(data)
                .when().post("/api/v1/users/register")
                .then().log().all()
                .extract().response();
    }

    public static Response login(User_POJO data){

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(data)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .extract().response();
    }
}
