package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 3/16/17.
 */

public class Recipes {
    String title, image;

    int ID, likes, ingredients;

    Recipes(int ID, String title, int likes, String image, int ing)

    {
        this.setID(ID);
        this.setTitle(title);
        this.setLikes(likes);
        this.setImage(image);
        this.setIngredients(ing);
    }
    public int getID() {
        return ID;

        }

    public void setID(int ID) {
        this.ID = ID;
        }
    public int getIngredients() {
        return ingredients;
    }
    public void setIngredients(int ingredients) {
        this.ingredients = ingredients;

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
