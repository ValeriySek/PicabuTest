package space.sekirin.pikabutest.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class PostContent {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("images")
    @Expose
    private List<String> images;
    @SerializedName("body")
    @Expose
    private String body;

    public PostContent(int id, String title, List<String> images, String body) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.body = body;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public List<String> getImages() {
        return images;
    }


    public String getBody() {
        return body;
    }

}
