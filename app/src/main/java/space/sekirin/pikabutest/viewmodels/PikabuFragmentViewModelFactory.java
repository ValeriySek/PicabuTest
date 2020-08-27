package space.sekirin.pikabutest.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import space.sekirin.pikabutest.data.ContentRepository;
import space.sekirin.pikabutest.data.FavouriteContentRepository;

public class PikabuFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ContentRepository mRepository;
    private FavouriteContentRepository mFavouriteContentRepository;

    public PikabuFragmentViewModelFactory(ContentRepository repository, FavouriteContentRepository favouriteContentRepository) {
        mRepository = repository;
        mFavouriteContentRepository = favouriteContentRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(PikabuFragmentViewModel.class)){
            return (T) new PikabuFragmentViewModel(mRepository, mFavouriteContentRepository);
        } else {
            throw new IllegalArgumentException("wrong class");
        }
    }
}
