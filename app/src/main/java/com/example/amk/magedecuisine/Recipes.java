package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 3/16/17.
 */

public class Recipes {
    String title;
    int id, likes;

    Recipes(int id, String title, int likes)
    {
        this.setId(id);
        this.setTitle(title);
        this.setLikes(likes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
