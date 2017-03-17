package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class RecipeBuilder extends AppCompatActivity {

    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_builder);
    }

    public void sendMessage(View view) {

        EditText editText = (EditText) findViewById(R.id.edit_message);
        String ingredients = editText.getText().toString();
        ingredients = ingredients.replaceAll("\\s+", "");
        ingredients = ingredients.replaceAll(",", "%2C");

        TextView txtView = (TextView) findViewById(R.id.textView1);
        txtView.setText(ingredients);

        new CallMashapeAsync().execute(ingredients);
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... ing) {

            String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=" + ing[0] + "&limitLicense=false&number=1&ranking=1";
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

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            answer = response.getBody().toString();
            TextView txtView = (TextView) findViewById(R.id.textView1);
            txtView.setText(answer);
        }
    }

    public void parseJSON(View view)
    {
        if (answer == null)
        {
            Toast.makeText(getApplicationContext(), "Renter JSON data", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data", answer);
            startActivity(intent);
        }
    }
}
