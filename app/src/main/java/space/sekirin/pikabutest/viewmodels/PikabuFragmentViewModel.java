package space.sekirin.pikabutest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import space.sekirin.pikabutest.data.ContentRepository;
import space.sekirin.pikabutest.data.FavouriteContentRepository;
import space.sekirin.pikabutest.data.models.PostContent;

public class PikabuFragmentViewModel extends ViewModel {

    private ContentRepository mRepository;
    private FavouriteContentRepository mFavouriteContentRepository;

    public PikabuFragmentViewModel(ContentRepository repository, FavouriteContentRepository favouriteContentRepository) {
        mRepository = repository;
        mFavouriteContentRepository = favouriteContentRepository;
    }

    public LiveData<List<PostContent>> getContent(){
        return mRepository.getContent();
    }

    public boolean isFavourite(int id){
        return mFavouriteContentRepository.isFavourite(id);
    }
}
