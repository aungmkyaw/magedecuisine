package com.example.amk.magedecuisine;

/**
 * Created by Rex on 3/9/2017.
 */

public class Recipe {
    private int _id;
    private String _recipename;
    private int _searchcode;

    public int get_ingredent() {
        return _ingredent;
    }

    public void set_ingredent(int _ingredent) {
        this._ingredent = _ingredent;
    }

    private int _ingredent;

    public Recipe(String recipename, int searchcode, String image, int likes, int ing)
    {
        this._recipename = recipename;
        this._searchcode = searchcode;
        this.image=image;
        this._likes=likes;
        this._ingredent=ing;
    }

    public int get_likes() {
        return _likes;
    }

    public void set_likes(int _likes) {
        this._likes = _likes;
    }

    private int _likes;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public int get_searchcode() {
        return _searchcode;
    }

    public void set_searchcode(int _searchcode) {
        this._searchcode = _searchcode;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_recipename(String _recipename) {
        this._recipename = _recipename;
    }

    public int get_id() {
        return _id;
    }

    public String get_recipename() {
        return _recipename;
    }
}
