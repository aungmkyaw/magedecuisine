package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
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
        String description;

        listView = (ListView) findViewById(R.id.listviewDT);
        recipesDetailAdapter = new RecipesDetailsAdapter(this, R.layout.activity_recipes_detail_builder);
        listView.setAdapter(recipesDetailAdapter);
        json_string = getIntent().getExtras().getString("json_dataDT");
        try {
            jsonObject = new JSONObject(json_string);
            //jsonArray = jsonObject.getJSONArray("");
         //   jsonObject = jsonObject.getJSONObject("");
            //jsonArray = new JSONArray(json_string);
           // int count = 0;

//            while(count < jsonArray.length())
//            {
                //JSONObject JO = jsonArray.getJSONObject(count);
               // description = JO.getString("summary");
                description = jsonObject.getString("summary");

                RecipeDetails recipeDetail = new RecipeDetails(title, likes, image, description);

                recipesDetailAdapter.add(recipeDetail);
                //count++;
         //   }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}