package com.example.admin.recipetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.admin.recipetest.model.Hit;
import com.example.admin.recipetest.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "mainActivityTag";

    Map<String, String> query = new ArrayMap<>();
    EditText etRecipe;

    List<Hit> hitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRecipe = findViewById(R.id.etRecipe);

    }

    public void searchRecipe(View view) {

        query.put("q", etRecipe.getText().toString());
        query.put("app_id", "f94f8c89");
        query.put("app_key", "983a41cebf141a1f43afe51f2f738a11");
        query.put("from", "0");
        query.put("to", "30");

        RetrofitHelper.getCall(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(Response response) {
                        for(Hit h: response.getHits()){
                            //Log.d(TAG, "onNext: " + h.getRecipe().getLabel());
                            hitList.add(h);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class );
                        intent.putExtra("recipe", (Serializable) hitList);
                        startActivity(intent);
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
