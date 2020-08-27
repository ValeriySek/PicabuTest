package space.sekirin.pikabutest.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static ApiFactory sApiFactory;
    private static Retrofit sRetrofit;

    private static final String BASE_URL = "https://pikabu.ru/page/interview/mobile-app/test-api/";

    private ApiFactory(){
        sRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ApiFactory getInstance(){
        if(sApiFactory == null){
            sApiFactory = new ApiFactory();
        }
        return sApiFactory;
    }

    public ApiService getApiService(){
        return sRetrofit.create(ApiService.class);
    }
}
