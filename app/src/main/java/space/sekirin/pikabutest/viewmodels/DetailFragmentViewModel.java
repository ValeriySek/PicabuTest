package space.sekirin.pikabutest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import space.sekirin.pikabutest.data.ContentRepository;
import space.sekirin.pikabutest.data.FavouriteContentRepository;
import space.sekirin.pikabutest.data.models.PostContent;

public class DetailFragmentViewModel extends ViewModel {

    private ContentRepository mContentRepository;
    private FavouriteContentRepository mFavouriteContentRepository;
    private int mPostId;
    MutableLiveData<Boolean> mBooleanMutableLiveData = new MutableLiveData<>();

    public DetailFragmentViewModel(ContentRepository contentRepository, FavouriteContentRepository favouriteContentRepository, int postId) {
        mContentRepository = contentRepository;
        mFavouriteContentRepository = favouriteContentRepository;
        mPostId = postId;
    }

    public LiveData<PostContent> getPost(){
        return mContentRepository.getPost(mPostId);
    }

    public LiveData<Boolean> isFavourite(){
        mBooleanMutableLiveData.setValue(mFavouriteContentRepository.isFavourite(mPostId));
        return mBooleanMutableLiveData;
    }

    public void addToFavourite(PostContent postContent){
        mFavouriteContentRepository.addToFavourite(postContent);
    }

    public void deleteFromFavourite(){
        mFavouriteContentRepository.deleteFavouriteMovie(
                mFavouriteContentRepository.getFavouritePostById(mPostId));
    }
}
