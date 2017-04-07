package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 3/29/17.
 */

public class RecipeDetails {
    String title, image, description;
    int likes;

    RecipeDetails(String title, int likes, String image)
    {
        this.setTitle(title);
        this.setLikes(likes);
        this.setImage(image);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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