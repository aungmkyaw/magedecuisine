package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayDetail extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RecipesDetailsAdapter recipesDetailAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);

        String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
        int likes = getIntent().getExtras().getInt("likesDT");
        listView = (ListView) findViewById(R.id.listviewDT);
        recipesDetailAdapter = new RecipesDetailsAdapter(this, R.layout.activity_recipes_detail_builder);
        listView.setAdapter(recipesDetailAdapter);
        json_string = getIntent().getExtras().getString("json_data");

        RecipeDetails recipeDetail = new RecipeDetails(title, likes, image);

        recipesDetailAdapter.add(recipeDetail);
//        try {
//            jsonObject = new JSONObject(json_string);
//            jsonArray = jsonObject.getJSONArray("");
//            int count = 0;

            //String title, image;
            //int likes, ingredientsLeft;

//            while(count < jsonArray.length())
//            {
//                JSONObject JO = jsonArray.getJSONObject(count);
//                title = JO.getString("title");
//                likes = JO.getInt("likes");
//                image = JO.getString("image");
//                ingredientsLeft = JO.getInt("missedIngredientCount");

                //RecipesDetails recipeDetail = new RecipesDetails(title, likes, image, ingredientsLeft);

                //recipesAdapter.add(recipe);
//                count++;
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
