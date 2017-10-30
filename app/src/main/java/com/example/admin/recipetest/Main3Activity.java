package com.example.admin.recipetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
    private Hit hit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ivRecipeHit = findViewById(R.id.ivRecipeHit);
        tvLabel = findViewById(R.id.tvLabel);
        tvSource = findViewById(R.id.tvSource);
        tvURL = findViewById(R.id.tvURL);
        lvIngredients = findViewById(R.id.lvIngredients);

        hit = (Hit) getIntent().getSerializableExtra("hit");


        Glide.with(this).load(hit.getRecipe().getImage()).into(ivRecipeHit);
        tvLabel.setText(hit.getRecipe().getLabel());
        tvSource.setText(hit.getRecipe().getSource());
        tvURL.setText(hit.getRecipe().getUrl());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                hit.getRecipe().getIngredientLines());

        lvIngredients.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lvIngredients);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void saveFavorite(View view) {
        HitUnit hitUnit = new HitUnit(
                hit.getRecipe().getLabel(),
                hit.getRecipe().getSource(),
                hit.getRecipe().getUrl(),
                hit.getRecipe().getImage(),
                hit.getRecipe().getIngredientLines());
    }
}
