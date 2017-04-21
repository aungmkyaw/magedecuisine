package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.List;

import static com.example.amk.magedecuisine.R.id.spinner;


public class RecipeBuilder extends AppCompatActivity {

    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_builder);

        final Spinner sItems = (Spinner) findViewById(spinner);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Dairy");
        spinnerArray.add("Meat");
        spinnerArray.add("Vegetables");
        spinnerArray.add("Fruits");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter);
    }

    //WHEN USER CLICKS GET RECIPES BUTTON
    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String ingredients = editText.getText().toString();

        if (ingredients.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Enter Ingredients!", Toast.LENGTH_LONG).show();
        }
        else
        {
            ingredients = ingredients.replaceAll("\\s+", "+"); //Remove ALL spaces in String
            ingredients = ingredients.replaceAll(",", "%2C"); //Replace commas in String for API CALL

            new CallMashapeAsync().execute(ingredients);
        }
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... ing) {

            String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=" + ing[0] + "&limitLicense=false&number=10&ranking=1";
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
