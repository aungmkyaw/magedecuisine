package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewSimilarRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_similar_recipes);

        //TO POPULATE SIMILAR RECIPES DATA
        GridView similarList = (GridView) findViewById(R.id.gridRecipes);
        SimilarRecAdapter similarRecAdapter = new SimilarRecAdapter(this, R.layout.simrecipes_layout);
        similarList.setAdapter(similarRecAdapter);

        String json_simRecipes = getIntent().getExtras().getString("json_simRecipes");

        //PARSING SIMILAR RECIPES
        String simImage, simTitle;
        int simCooktime, simRecipescount = 0;

        try
        {
            JSONArray jsonSimarray = new JSONArray(json_simRecipes);
            while (simRecipescount < jsonSimarray.length())
            {
                JSONObject JOsim = jsonSimarray.getJSONObject(simRecipescount);
                simTitle = JOsim.getString("title");
                simCooktime = JOsim.getInt("readyInMinutes");
                simImage = "https://spoonacular.com/recipeImages/" + JOsim.getString("image");
                SimilarRecipes similarRecipes = new SimilarRecipes(simTitle, simCooktime, simImage);
                similarRecAdapter.add(similarRecipes);
                simRecipescount++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
