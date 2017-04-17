package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 4/16/17.
 */

public class SimilarRecipes {

    String image, title;
    int likes;

    public SimilarRecipes(String name, int noLikes, String pic)
    {
        this.setTitle(name);
        this.setLikes(noLikes);
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
