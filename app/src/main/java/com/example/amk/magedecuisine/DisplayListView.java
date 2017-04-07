package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity{

    JSONArray jsonArray;
    RecipesAdapter recipesAdapter;
    ListView listView;
    String title, image, json_string;
    int recipeID, likes, ingredientsLeft, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView) findViewById(R.id.listview);
        recipesAdapter = new RecipesAdapter(this, R.layout.row_layout);
        listView.setAdapter(recipesAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonArray = new JSONArray(json_string);
            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                recipeID = JO.getInt("id");
                title = JO.getString("title");
                likes = JO.getInt("likes");
                image = JO.getString("image");
                ingredientsLeft = JO.getInt("missedIngredientCount");

                Recipes recipe = new Recipes(title, likes, image, ingredientsLeft);

                recipesAdapter.add(recipe);
                count++;
            }

            AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View v, int position, long id)
                {
                    Intent intent = new Intent(getApplicationContext(), RecipesDetailBuilder.class);
                    intent.putExtra("idDT", recipeID);
                    intent.putExtra("titleDT", title);
                    intent.putExtra("likesDT", likes);
                    intent.putExtra("imageDT", image);
                    startActivity(intent);
                }
            };
            listView.setOnItemClickListener(itemClickListener);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}