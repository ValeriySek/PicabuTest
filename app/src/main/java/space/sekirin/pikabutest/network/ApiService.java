package space.sekirin.pikabutest.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import space.sekirin.pikabutest.data.models.PostContent;

public interface ApiService {

    @GET("feed.php")
    Observable<List<PostContent>> getContent();

    @GET("story.php?")
    Observable<PostContent> getSpecificPost(@Query("id") int postId);
}
