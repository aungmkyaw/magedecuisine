package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 3/16/17.
 */

public class Recipes {
    String title, image;
    int likes, ingredientsLeft;

    Recipes(String title, int likes, String image, int ingredientsLeft)
    {
        this.setTitle(title);
        this.setLikes(likes);
        this.setImage(image);
        this.setIngredientsLeft(ingredientsLeft);
    }

    public int getIngredientsLeft() {
        return ingredientsLeft;
    }

    public void setIngredientsLeft(int ingredientsLeft) {
        this.ingredientsLeft = ingredientsLeft;
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
