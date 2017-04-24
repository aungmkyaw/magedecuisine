package com.example.amk.magedecuisine;

/**
 * Created by AMK on 4/24/2017.
 */

public class Ingredient {
    private String _ingredient;

    public Ingredient(String ingredientname)
    {
        this._ingredient = ingredientname;
    }

    public void set_ingredientname(String _ingredient) {
        this._ingredient = _ingredient;
    }

    public String get_ingredient() {
        return _ingredient;
    }
}