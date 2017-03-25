package com.example.amk.magedecuisine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RecipesAdapter recipesAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView) findViewById(R.id.listview);
        recipesAdapter = new RecipesAdapter(this, R.layout.row_layout);
        listView.setAdapter(recipesAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            //jsonObject = new JSONObject(json_string);
            //jsonArray = jsonObject.getJSONArray("");
            jsonArray = new JSONArray(json_string);
            int count = 0;
            String title, image;
            int likes;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                title = JO.getString("title");
                likes = JO.getInt("likes");
                image = JO.getString("image");

                Recipes recipe = new Recipes(title, likes, image);

                recipesAdapter.add(recipe);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}