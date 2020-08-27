package space.sekirin.pikabutest.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import space.sekirin.pikabutest.data.models.PostContent;
import space.sekirin.pikabutest.network.ApiFactory;
import space.sekirin.pikabutest.network.ApiService;

public class ContentRepository {

    private static ContentRepository sContentRepository;

    private ApiService apiService;

    private ContentRepository() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        apiService = apiFactory.getApiService();
    }

    public static ContentRepository getInstance(){
        if(sContentRepository == null){
            sContentRepository = new ContentRepository();
        }
        return sContentRepository;
    }

    public MutableLiveData<List<PostContent>> getContent(){
        final MutableLiveData<List<PostContent>> content = new MutableLiveData<>();

        apiService.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PostContent>>() {
                    @Override
                    public void accept(List<PostContent> postContents) throws Exception {

                        Log.i("Problem", "ContentRepository" + postContents);
                        content.setValue(postContents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        return content;
    }

    public MutableLiveData<PostContent> getPost(int postId){
        final MutableLiveData<PostContent> postContent = new MutableLiveData<>();

        apiService.getSpecificPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PostContent>() {
                    @Override
                    public void accept(PostContent post) throws Exception {

                        Log.i("Problem", "ContentRepository" + post);
                        postContent.setValue(post);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        return postContent;
    }

}
