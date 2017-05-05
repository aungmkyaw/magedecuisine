package com.example.amk.magedecuisine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;

import static com.example.amk.magedecuisine.R.id.listview;

public class Pantry extends AppCompatActivity {

    MyDBHandler dbHandler;
    ArrayAdapter<String> adapter;
    ListView pantryView;
    EditText entItem;
    String answer;
    RecipeBuilder recipeBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        pantryView = (ListView) findViewById(R.id.pantryView);
        entItem = (EditText) findViewById(R.id.entItem);
        final Button addBtn = (Button) findViewById(R.id.addBtn);
        final Button searchBtn = (Button) findViewById(R.id.searchBtn);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
        pantryView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String inputText = (String) pantryView.getItemAtPosition(i);
                Log.d("DeletedText", inputText);
                dbHandler.deleteIngredients(inputText);
                pantryView.removeViewInLayout(view);
                printDatabase();
                return false;
            }
        });
    }

    public void addBtnClicked(View view) {
        String ingredients = entItem.getText().toString().toString();
        if (ingredients.matches("") || ingredients.matches(" ")) {
            Toast.makeText(getApplicationContext(), "Enter Ingredients!", Toast.LENGTH_LONG).show();
        } else {
            Ingredient ingredient = new Ingredient(entItem.getText().toString().toString());
            dbHandler.addIngredient(ingredient);
        }
        printDatabase();
    }


    public void printDatabase() {
        ArrayList<String> ings = dbHandler.ingToList();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ings){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                return view;
            }
        };
        pantryView.setAdapter(adapter);
        entItem.setText("");
    }


    public void sendMessage(View view) {
        String pantryIngredients = dbHandler.ingSearchString();
        Log.d("tag1", pantryIngredients);
        String ingredients = pantryIngredients;

        if (ingredients.matches("")) {
            Toast.makeText(getApplicationContext(), "Enter Ingredients!", Toast.LENGTH_LONG).show();
        } else {
            ingredients = ingredients.replaceAll("\\s+", "+"); //Remove ALL spaces in String
            ingredients = ingredients.replaceAll(",", "%2C"); //Replace commas in String for API CALL

            new Pantry.CallMashapeAsync().execute(ingredients);
        }
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... ing) {

            String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=" + ing[0] + "&limitLicense=false&number=50&ranking=1";
            String APIKey = "KgebgXWQeHmshgowAPA7lmc3utfAp1Vu0jyjsnN2rSrkXexgCY";
            HttpResponse<JsonNode> request = null;
            try {
                request = Unirest.get(URL)
                        .header("X-Mashape-Key", APIKey)
                        .header("Accept", "application/json")
                        .asJson();
            } catch (UnirestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return request;
        }

        @Override
        protected void onProgressUpdate(Integer...integers) {
        }

        @Override
        protected void onPostExecute(HttpResponse<JsonNode> response) {
            answer = response.getBody().toString();

            Intent intent = new Intent(getApplicationContext(), DisplayListView.class);
            intent.putExtra("json_data", answer);
            startActivity(intent);
        }
    }
}
