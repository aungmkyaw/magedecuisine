package com.example.amk.magedecuisine;

/**
 * Created by sukhdipsingh on 4/14/17.
 */

public class RecipeInstructions {

    int number;
    String step, equipmentName, equipmentImage;

    RecipeInstructions(){}

    RecipeInstructions(int number, String step, String equipmentName, String equipmentImage)
    {
        this.setNumber(number);
        this.setStep(step);
        this.setEquipmentName(equipmentName);
        this.setEquipmentImage(equipmentImage);
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }
}
