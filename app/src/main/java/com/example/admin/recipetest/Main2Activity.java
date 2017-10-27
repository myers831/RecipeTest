package com.example.admin.recipetest;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.recipetest.model.Hit;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView rvRecipe;
    RecyclerView.LayoutManager layoutManager;
    RecycleViewAdapter adapter;
    List<Hit> hitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvRecipe = findViewById(R.id.rvRecipe);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = new GridLayoutManager(this, 5);
        }else{
            layoutManager = new GridLayoutManager(this, 3);
        }
        hitList = (List<Hit>) getIntent().getSerializableExtra("recipe");

        adapter = new RecycleViewAdapter(hitList);

        rvRecipe.setLayoutManager(layoutManager);
        rvRecipe.setAdapter(adapter);

    }
}
