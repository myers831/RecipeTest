package com.example.admin.recipetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.recipetest.model.Hit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/14/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    List<Hit> hitList = new ArrayList<>();
    Context context;

    public RecycleViewAdapter(List<Hit> hitList) {
        this.hitList = hitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hit hit = hitList.get(position);
        holder.hit = hit;

        Glide.with(context).load(hit.getRecipe().getImage()).into(holder.ivRecipe);

    }

    @Override
    public int getItemCount() {
        return hitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivRecipe;
        Hit hit;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Main3Activity.class);
                    intent.putExtra("hit", hit);
                    context.startActivity(intent);
                }
            });

            ivRecipe = itemView.findViewById(R.id.ivRecipe);
        }
    }
}
