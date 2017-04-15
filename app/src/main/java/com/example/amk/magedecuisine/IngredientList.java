package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 4/14/17.
 */

public class IngredientList {

    String name, unit, image;
    int amount;

    IngredientList(String name, String unit, int amount, String image)
    {
        this.setName(name);
        this.setUnit(unit);
        this.setAmount(amount);
        this.setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
