package com.example.amk.magedecuisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        listView.setAdapter(recipesAdapter);
        recipesAdapter = new RecipesAdapter(this, R.layout.row_layout);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String id, title, likes;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                id = JO.getString("id");
                title = JO.getString("title");
                likes = JO.getString("likes");

                Recipes recipe = new Recipes(id, title, likes);
                recipesAdapter.add(recipe);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}