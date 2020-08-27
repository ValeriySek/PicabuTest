package space.sekirin.pikabutest.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import space.sekirin.pikabutest.data.models.FavouritePostContent;
import space.sekirin.pikabutest.data.models.PostContent;

public class FavouriteContentRepository {

    private static FavouriteContentRepository mFavouriteContentRepository;
    private static FavouriteContentDao mFavouriteContentDao;


    private FavouriteContentRepository(FavouriteContentDao favouriteContentDao) {
        mFavouriteContentDao = favouriteContentDao;
    }

    public static FavouriteContentRepository getInstance(FavouriteContentDao favouriteContentDao){
        if(mFavouriteContentRepository == null){
            mFavouriteContentRepository = new FavouriteContentRepository(favouriteContentDao);
        }
        return mFavouriteContentRepository;
    }

    public LiveData<List<FavouritePostContent>> getAllPosts(){
        return mFavouriteContentDao.getAllPosts();
    }

    public boolean isFavourite(int postId){
        try {
            return new IsFavouriteTask().execute(postId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class IsFavouriteTask extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... movies) {
            if (movies != null && movies.length > 0) {
                return mFavouriteContentDao.isFavourite(movies[0]);
            }
            return null;
        }
    }

    public void addToFavourite(PostContent postContent){
        new InsertFavouriteTask().execute(new FavouritePostContent(postContent));
    }

    private static class InsertFavouriteTask extends AsyncTask<FavouritePostContent, Void, Void> {

        @Override
        protected Void doInBackground(FavouritePostContent... movies) {
            if (movies != null && movies.length > 0) {
                mFavouriteContentDao.insertPostToFavourite(movies[0]);
            }
            return null;
        }
    }
    public void deleteFavouriteMovie(FavouritePostContent postContent){
        new DeleteFavouriteTask().execute(postContent);
    }

    private static class DeleteFavouriteTask extends AsyncTask<FavouritePostContent, Void, Void> {

        @Override
        protected Void doInBackground(FavouritePostContent... movies) {
            if (movies != null && movies.length > 0) {
                mFavouriteContentDao.deletePostFromFavourite(movies[0]);
            }
            return null;
        }
    }

    public FavouritePostContent getFavouritePostById(int id){
        try {
            return new GetFavouritePostTask().execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetFavouritePostTask extends AsyncTask<Integer, Void, FavouritePostContent> {

        @Override
        protected FavouritePostContent doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return mFavouriteContentDao.getPostById(integers[0]);
            }
            return null;
        }
    }

}
