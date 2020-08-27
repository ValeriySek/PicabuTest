package space.sekirin.pikabutest.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import space.sekirin.pikabutest.data.ContentRepository;
import space.sekirin.pikabutest.data.FavouriteContentRepository;

public class DetailFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ContentRepository mContentRepository;
    private FavouriteContentRepository mFavouriteContentRepository;
    private int mPostId;

    public DetailFragmentViewModelFactory(ContentRepository contentRepository, FavouriteContentRepository favouriteContentRepository, int postId) {
        mContentRepository = contentRepository;
        mFavouriteContentRepository = favouriteContentRepository;
        mPostId = postId;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DetailFragmentViewModel.class)){
            return (T) new DetailFragmentViewModel(mContentRepository, mFavouriteContentRepository, mPostId);
        } else {
            throw new IllegalArgumentException("wrong class");
        }
    }
}
