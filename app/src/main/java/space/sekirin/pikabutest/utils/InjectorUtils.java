package space.sekirin.pikabutest.utils;

import android.content.Context;

import space.sekirin.pikabutest.data.ContentDatabase;
import space.sekirin.pikabutest.data.ContentRepository;
import space.sekirin.pikabutest.data.FavouriteContentRepository;
import space.sekirin.pikabutest.viewmodels.DetailFragmentViewModelFactory;
import space.sekirin.pikabutest.viewmodels.FavouriteFragmentViewModelFactory;
import space.sekirin.pikabutest.viewmodels.PikabuFragmentViewModelFactory;

public class InjectorUtils {

    private static ContentRepository getContentRepository(){
        return ContentRepository.getInstance();
    }

    public static FavouriteContentRepository getFavouriteContentRepository(Context context){
        return FavouriteContentRepository.getInstance(ContentDatabase.getInstance(context).favouriteContentDao());
    }

    public static PikabuFragmentViewModelFactory providePikabuFragmentViewModelFactory(Context context){
        return new PikabuFragmentViewModelFactory(getContentRepository(), getFavouriteContentRepository(context));
    }

    public static DetailFragmentViewModelFactory provideDetailFragmentViewModelFactory(Context context, int postId){
        return new DetailFragmentViewModelFactory(getContentRepository(), getFavouriteContentRepository(context), postId);
    }

    public static FavouriteFragmentViewModelFactory provideFavouriteFragmentViewModelFactory(Context context){
        return new FavouriteFragmentViewModelFactory(getFavouriteContentRepository(context));
    }
}
