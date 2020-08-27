package space.sekirin.pikabutest.data.models;

import androidx.room.Entity;

import java.util.List;

import space.sekirin.pikabutest.data.FavouriteContentRepository;

@Entity(tableName = "favourite_post")
public class FavouritePostContent extends PostContent {

    public FavouritePostContent(int id, String title, List<String> images, String body) {
        super(id, title, images, body);
    }

    public FavouritePostContent(PostContent content){
        super(content.getId(), content.getTitle(), content.getImages(), content.getBody());
    }
}
