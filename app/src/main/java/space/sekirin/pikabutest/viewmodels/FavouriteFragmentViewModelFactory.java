package space.sekirin.pikabutest.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import space.sekirin.pikabutest.data.FavouriteContentRepository;

public class FavouriteFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private FavouriteContentRepository mFavouriteContentRepository;

    public FavouriteFragmentViewModelFactory(FavouriteContentRepository favouriteContentRepository) {
        mFavouriteContentRepository = favouriteContentRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavouriteFragmentViewModel.class)){
            return (T) new FavouriteFragmentViewModel(mFavouriteContentRepository);
        } else {
            throw new IllegalArgumentException("wrong class");
        }
    }
}
