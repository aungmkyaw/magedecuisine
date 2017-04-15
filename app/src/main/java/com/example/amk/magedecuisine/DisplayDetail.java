package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);

        String json_string, ingredientAmount, ingredientImage;
        int cookTime, count = 0;
        JSONObject jsonObject;
        JSONArray jsonArray;
        RecipesDetailsAdapter recipesDetailAdapter;
        IngredientsAdapter ingredientsAdapter;
        ListView listView;

        String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
        int likes = getIntent().getExtras().getInt("likesDT");

        listView = (ListView) findViewById(R.id.listviewDT);
        recipesDetailAdapter = new RecipesDetailsAdapter(this, R.layout.activity_recipes_detail_builder);
        ingredientsAdapter = new IngredientsAdapter(this, R.layout.ingredient_layout);
        listView.setAdapter(recipesDetailAdapter);
        json_string = getIntent().getExtras().getString("json_dataDT");
        try {
            jsonObject = new JSONObject(json_string);
            cookTime = jsonObject.getInt("readyInMinutes");

            jsonArray = jsonObject.getJSONArray("extendedIngredients");

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                ingredientAmount = JO.getString("originalString");
                ingredientImage = JO.getString("image");

                IngredientList ingList = new IngredientList(ingredientAmount, ingredientImage);
                ingredientsAdapter.add(ingList);
                count++;
            }

           // RecipeDetails recipeDetail = new RecipeDetails(title, likes, image, cookTime, ingList);
            //recipesDetailAdapter.add(recipeDetail);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}