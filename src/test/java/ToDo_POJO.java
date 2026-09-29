import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ToDo_POJO {

    private String item;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @JsonProperty("_id")
    private String ID;

    private String userID;

    private String createdAt;

    @JsonProperty("__v")
    private String V;


    public ToDo_POJO(String item, Boolean isCompleted){
        this.item=item;
        this.isCompleted=isCompleted;
    }

    public ToDo_POJO(String item){
        this.item=item;
    }

public ToDo_POJO(){

}

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    @JsonProperty("isCompleted")
    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @JsonProperty("_id")
    public String getID() {
        return ID;
    }

    @JsonProperty("_id")
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String getV() {
        return V;
    }

    @JsonProperty("__v")
    public void setV(String v) {
        V = v;
    }
}
