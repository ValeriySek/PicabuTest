package space.sekirin.pikabutest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import space.sekirin.pikabutest.data.models.FavouritePostContent;

@Dao
public interface FavouriteContentDao {

    @Query("SELECT * FROM favourite_post")
    LiveData<List<FavouritePostContent>> getAllPosts();

    @Query("SELECT * FROM favourite_post WHERE id = :postId" )
    FavouritePostContent getPostById(int postId);

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_post WHERE id = :postId LIMIT 1)")
    boolean isFavourite(int postId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPostToFavourite(FavouritePostContent postContent);

    @Delete
    void deletePostFromFavourite(FavouritePostContent postContent);

}
