package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 4/14/17.
 */

public class IngredientList {

    String amount, image;

    IngredientList(String amount, String image)
    {
        this.setAmount(amount);
        this.setImage(image);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
