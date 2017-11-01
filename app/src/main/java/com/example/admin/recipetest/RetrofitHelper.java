package com.example.admin.recipetest;

import android.content.Context;
import android.util.Log;

import com.example.admin.recipetest.model.Response;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Admin on 10/27/2017.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://api.edamam.com";



    public static Retrofit create(){

        int cacheSize = 10 * 1024 * 1024;
        //Context context = null;


       // Cache cache = new Cache(context.getCacheDir(), cacheSize);

        //Create Logging Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                //.cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Observable<Response> getCall(Map<String, String> query) {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Log.d(TAG, "getCall: ");
        return service.getResponse(query);
    }

    public interface RetrofitService {
        @GET("/search")
        Observable<Response> getResponse(@QueryMap Map<String, String> query);

    }
}
