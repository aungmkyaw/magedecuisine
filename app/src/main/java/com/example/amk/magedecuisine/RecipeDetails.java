package com.example.amk.magedecuisine;

import java.util.ArrayList;

/**
 * Created by sukhdipsingh on 3/29/17.
 */

public class RecipeDetails {
    String title, image, description;
    int likes, cookTime;
    ArrayList<String> ingredientList, recipeInstructions;

    RecipeDetails(String title, int likes, String image, int cookTime, ArrayList<String> ingList)
    {
        this.setTitle(title);
        this.setLikes(likes);
        this.setImage(image);
        this.setCookTime(cookTime);
        this.setIngredientList(ingList);
    }

    public ArrayList<String> getIngredientList() {
        return ingredientList;
    }
    public void setIngredientList(ArrayList<String> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public ArrayList<String> getRecipeInstructions() {
        return recipeInstructions;
    }
    public void setRecipeInstructions(ArrayList<String> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }
    public int getCookTime() {
        return cookTime;
    }
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
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