package com.example.amk.magedecuisine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.style.ClickableSpan;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;

public class Pantry extends AppCompatActivity {

    MyDBHandler dbHandler;
    TextView pantryView;
    EditText entItem;
    String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        pantryView = (TextView) findViewById(R.id.pantryView);
        entItem = (EditText) findViewById(R.id.entItem);
        final Button addBtn = (Button) findViewById(R.id.addBtn);
        final Button rmvBtn = (Button) findViewById(R.id.rmvBtn);
        final Button searchBtn = (Button) findViewById(R.id.searchBtn);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public void addBtnClicked(View view)
    {
        String ingredients = entItem.getText().toString().toString();
        if (ingredients.matches("") || ingredients.matches(" "))
        {
            Toast.makeText(getApplicationContext(), "Enter Ingredients!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Ingredient ingredient = new Ingredient(entItem.getText().toString().toString());
            dbHandler.addIngredient(ingredient);
        }
        printDatabase();
    }

    public void rmvBtnClicked(View view)
    {
        //Need to use clickable span to select the text in the text view to remove here adding to inputText
        String inputText = entItem.getText().toString();
        dbHandler.deleteIngredients(inputText);
        printDatabase();
    }


    public void printDatabase()
    {
        String dbString = dbHandler.ingredientToString();
        pantryView.setText(dbString);
        entItem.setText("");
    }

    public void sendMessage(View view) {
        String pantryIngredients = dbHandler.ingSearchString();
        Log.d("tag1", pantryIngredients);
        String ingredients = pantryIngredients;

        if (ingredients.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Enter Ingredients!", Toast.LENGTH_LONG).show();
        }
        else
        {
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
    }

    protected void onProgressUpdate(Integer...integers) {
    }


    protected void onPostExecute(HttpResponse<JsonNode> response) {
        answer = response.getBody().toString();

        Intent intent = new Intent(getApplicationContext(), DisplayListView.class);
        intent.putExtra("json_data", answer);
        startActivity(intent);
    }

}
