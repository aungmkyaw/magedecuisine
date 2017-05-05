package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Button;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayDetail extends AppCompatActivity {

    String json_string, json_simRecipes;
    MyDBHandler dbHandler;
    int recipeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);
        dbHandler = new MyDBHandler(this, null, null, 1);
        //TO POPULATE BASIC RECIPE DETAIL DATA
        ListView listView = (ListView) findViewById(R.id.listviewDT);
        RecipesDetailsAdapter recipesDetailAdapter = new RecipesDetailsAdapter(this, R.layout.activity_recipes_detail_builder);
        listView.setAdapter(recipesDetailAdapter);

        //TO POPULATE RECIPE INGREDIENTS DATA
        GridView ingredientsList = (GridView) findViewById(R.id.gridIngredients);
        Button bookMark = (Button) findViewById(R.id.bookmark);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, R.layout.ingredient_layout);
        ingredientsList.setAdapter(ingredientsAdapter);
        json_string = getIntent().getExtras().getString("json_dataDT");
        json_simRecipes = getIntent().getExtras().getString("json_simRecipes");
        recipeID = getIntent().getExtras().getInt("recipeID");
        Log.d("RecipeIDinDisplayDetail", Integer.toString(recipeID));
        if(dbHandler.checkRecipeStored(recipeID))
        {
            bookMark.setText("Remove");
        }
        else
        {
            bookMark.setText("Bookmark");
        }
        try {
            JSONObject jsonObject = new JSONObject(json_string);

            //PARSING BASIC RECIPE DETAIL DATA
            String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
            int likes = getIntent().getExtras().getInt("likesDT");
            int cookTime = jsonObject.getInt("readyInMinutes");

            RecipeDetails recipeDetail = new RecipeDetails(title, likes, image, cookTime);
            recipesDetailAdapter.add(recipeDetail);


            //PARSING INGREDIENTS NEEDED FOR RECIPE
            String ingredientAmount, ingredientImage;
            int ingredientCount = 0;

            JSONArray jsonArray = jsonObject.getJSONArray("extendedIngredients");
            while(ingredientCount < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(ingredientCount);
                ingredientAmount = JO.getString("originalString");
                ingredientImage = JO.getString("image");

                IngredientList ingList = new IngredientList(ingredientAmount, ingredientImage);
                ingredientsAdapter.add(ingList);
                ingredientCount++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewSimilarRecipes(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ViewSimilarRecipes.class);
        intent.putExtra("json_simRecipes", json_simRecipes);
        startActivity(intent);
    }

    public void viewRecipeInstructions(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ViewRecipeInstructions.class);
        intent.putExtra("json_data", json_string);
        startActivity(intent);
    }

    public void bookMark(View view)
    {
        Button bookMark = (Button) findViewById(R.id.bookmark);
        if(dbHandler.checkRecipeStored(recipeID))
        {
            dbHandler.deleteRecipe(getIntent().getExtras().getInt("recipeID"));
            bookMark.setText("Bookmark");
        }
        else
        {
            Recipe recipe = new Recipe(getIntent().getExtras().getString("titleDT"),recipeID,getIntent().getExtras().getString("imageDT"), getIntent().getExtras().getInt("likesDT"));
            dbHandler.addRecipe(recipe);
            bookMark.setText("Remove");
        }

        //Log.d("RecipeID", );

    }
}