package com.example.admin.recipetest;

import android.util.Log;

import com.example.admin.recipetest.model.Response;

import java.util.Map;

import io.reactivex.Observable;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
