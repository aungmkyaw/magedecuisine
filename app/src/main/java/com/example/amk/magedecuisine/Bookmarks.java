package com.example.amk.magedecuisine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Bookmarks extends AppCompatActivity {

    EditText bmInput;
    TextView bookMarkView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        final RecipeAdapter recipeAdapter;
        dbHandler = new MyDBHandler(this, null, null, 1);
        ListView listView;
        listView = (ListView) findViewById(R.id.listview);
        recipeAdapter = new RecipeAdapter(this, R.layout.row_layout);
        listView.setAdapter(recipeAdapter);
        ArrayList<Recipe> temp = new ArrayList<Recipe>();
        temp = dbHandler.getBookmarks();
        for(int x = 0; x < temp.size(); x++)
        {
            recipeAdapter.add(temp.get(x));
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View v, int position, long id)
            {
                //Suk I need you to build something similar to CallMashapeAsync().execute(ingredients); in RecipeBuilder only it takes the ID as search instead of ingredient and then pass the result to RecipesDetailBuilder

            }
        };
        listView.setOnItemClickListener(itemClickListener);

    }



    public void printDatabase()
    {
        String dbString = dbHandler.databaseToString();
        bookMarkView.setText(dbString);
        bmInput.setText("");
    }



}
