package space.sekirin.pikabutest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import space.sekirin.pikabutest.data.FavouriteContentRepository;
import space.sekirin.pikabutest.data.models.FavouritePostContent;

public class FavouriteFragmentViewModel extends ViewModel {

    private FavouriteContentRepository mFavouriteContentRepository;

    public FavouriteFragmentViewModel(FavouriteContentRepository favouriteContentRepository) {
        mFavouriteContentRepository = favouriteContentRepository;
    }

    public LiveData<List<FavouritePostContent>> getPosts(){
        return mFavouriteContentRepository.getAllPosts();
    }
}
