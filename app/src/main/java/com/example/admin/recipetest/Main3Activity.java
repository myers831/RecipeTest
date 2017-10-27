package com.example.admin.recipetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.recipetest.model.Hit;
import com.example.admin.recipetest.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    ImageView ivRecipeHit;
    TextView tvLabel, tvSource, tvURL;
    ListView lvIngredients;
    List<String> ingredientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ivRecipeHit = findViewById(R.id.ivRecipeHit);
        tvLabel = findViewById(R.id.tvLabel);
        tvSource = findViewById(R.id.tvSource);
        tvURL = findViewById(R.id.tvURL);
        lvIngredients = findViewById(R.id.lvIngredients);

        Hit hit = (Hit) getIntent().getSerializableExtra("hit");


        Glide.with(this).load(hit.getRecipe().getImage()).into(ivRecipeHit);
        tvLabel.setText(hit.getRecipe().getLabel());
        tvSource.setText(hit.getRecipe().getSource());
        tvURL.setText(hit.getRecipe().getUrl());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                hit.getRecipe().getIngredientLines());

        lvIngredients.setAdapter(adapter);

    }
}
