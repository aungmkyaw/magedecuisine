package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 3/16/17.
 */

public class Recipes {
    String title, image;
    int ID, likes, ingredientsLeft;

    Recipes(int ID, String title, int likes, String image, int ingredientsLeft)
    {
        this.setID(ID);
        this.setTitle(title);
        this.setLikes(likes);
        this.setImage(image);
        this.setIngredientsLeft(ingredientsLeft);
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
