package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 4/16/17.
 */

public class SimilarRecipes {

    String image, title;
    int cookTime;

    public SimilarRecipes(String name, int timeTocook, String pic)
    {
        this.setTitle(name);
        this.setCookTime(timeTocook);
        this.setImage(pic);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }
}
