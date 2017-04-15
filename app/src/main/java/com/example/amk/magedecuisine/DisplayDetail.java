package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);

        String json_string;
        JSONObject jsonObject;
        JSONArray jsonArray;
        RecipesDetailsAdapter recipesDetailAdapter;
        ListView listView;
        int cookTime;
        ArrayList<String> ingredientList = new ArrayList<String>();

        String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
        int likes = getIntent().getExtras().getInt("likesDT");

        listView = (ListView) findViewById(R.id.listviewDT);
        recipesDetailAdapter = new RecipesDetailsAdapter(this, R.layout.activity_recipes_detail_builder);
        listView.setAdapter(recipesDetailAdapter);
        json_string = getIntent().getExtras().getString("json_dataDT");
        try {
            jsonObject = new JSONObject(json_string);
            cookTime = jsonObject.getInt("readyInMinutes");

            jsonArray = jsonObject.getJSONArray("extendedIngredients");
            //jsonArray = new JSONArray("ingredients");
            int count = 0;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                ingredientList.add(count, JO.getString("name"));

                count++;
            }

            RecipeDetails recipeDetail = new RecipeDetails(title, likes, image, cookTime, ingredientList);

            recipesDetailAdapter.add(recipeDetail);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}