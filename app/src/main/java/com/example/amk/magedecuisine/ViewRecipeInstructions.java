package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewRecipeInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_instructions);

        //TO POPULATE RECIPE INSTRUCTIONS DATA
        ListView instructionsList = (ListView) findViewById(R.id.listviewInstructions);
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(this, R.layout.instructions_layout);
        instructionsList.setAdapter(instructionsAdapter);


        String json_string = getIntent().getExtras().getString("json_data");

        //PARSING INSTRUCTIONS AND EQUIPMENT NEEDED FOR RECIPE
        int instructNum;
        String instructStep, equipmentName, equipmentImage;

        try {

            JSONObject mainObj = new JSONObject(json_string);
            if (mainObj != null) {
                JSONArray list = mainObj.getJSONArray("analyzedInstructions");
                if (list != null) {
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject elem = list.getJSONObject(i);
                        if (elem != null) {
                            JSONArray steps = elem.getJSONArray("steps");
                            if (steps != null) {
                                for (int j = 0; j < steps.length(); j++) {
                                    JSONObject innerElem = steps.getJSONObject(j);
                                    if (innerElem != null) {
                                        instructNum = innerElem.getInt("number");
                                        instructStep = innerElem.getString("step");

                                        JSONArray equipment = innerElem.getJSONArray("equipment");
                                        if (equipment.isNull(0)) {
                                            RecipeInstructions recipeInstructions = new RecipeInstructions(instructNum, instructStep);
                                            instructionsAdapter.add(recipeInstructions);
                                        }
                                        for (int k = 0; k < equipment.length(); k++) {
                                            JSONObject equiplist = equipment.getJSONObject(k);
                                            if (equipment != null) {
                                                equipmentName = equiplist.getString("name");
                                                equipmentImage = equiplist.getString("image");
                                                RecipeInstructions recipeInstructions = new RecipeInstructions(instructNum, instructStep, equipmentName, equipmentImage);
                                                instructionsAdapter.add(recipeInstructions);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
