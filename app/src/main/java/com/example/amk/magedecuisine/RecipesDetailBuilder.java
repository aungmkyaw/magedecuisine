package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RecipesDetailBuilder extends AppCompatActivity {

    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CallMashapeAsync().execute();
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... ing) {

            int recipeID = getIntent().getExtras().getInt("idDT");
            String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + recipeID + "/information?includeNutrition=true";
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
            String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
            int likes = getIntent().getExtras().getInt("likesDT"), recipeID = getIntent().getExtras().getInt("idDT");;

            answer = response.getBody().toString();

            Intent intent = new Intent(getApplicationContext(), SimilarRecipesBuilder.class);
            intent.putExtra("json_dataDT", answer);
            intent.putExtra("IDforSim", recipeID);
            intent.putExtra("titleDT", title);
            intent.putExtra("likesDT", likes);
            intent.putExtra("imageDT", image);
            startActivity(intent);
            overridePendingTransition(0, 0);//NO ACTIVITY ANIMATION
            finish();
        }
    }
}
