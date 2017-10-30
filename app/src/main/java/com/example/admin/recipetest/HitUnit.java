package com.example.admin.recipetest;

import java.util.List;

/**
 * Created by Admin on 10/30/2017.
 */

public class HitUnit {
    String Label, Source, Url, Image;
    List<String> ingredientsList;

    public HitUnit(String label, String source, String url, String image, List<String> ingredientsList) {
        this.Label = label;
        this.Source = source;
        this.Url = url;
        this.Image = image;
        this.ingredientsList = ingredientsList;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public List<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
