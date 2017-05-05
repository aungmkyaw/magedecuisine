package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Bookmarks extends AppCompatActivity {

    EditText bmInput;
    TextView bookMarkView;
    MyDBHandler dbHandler;
    int recipeID, likes;
    Recipes recipes;
    String name, imageurl;

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
            Log.d("list",temp.get(x).get_searchcode()+"");
            recipeAdapter.add(temp.get(x));
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View v, int position, long id)
            {
                //Suk I need you to build something similar to CallMashapeAsync().execute(ingredients); in RecipeBuilder only it takes the ID as search instead of ingredient and then pass the result to RecipesDetailBuilder
                Recipe obj = recipeAdapter.getItem(position);
                //This is the ID you should use to search with the api
                recipeID = obj.get_searchcode();

                name = obj.get_recipename();
                likes = obj.get_likes();
                imageurl = obj.getImage();
                Log.d("ID when saved", ""+recipeID + " " + name + " "  + imageurl );

                new CallMashapeAsync().execute();

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


    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... ing) {

            //Suk need a url similar to RecipeBuilder but take recipeID and give back the same info. Right now this URL doesn't even give back the likes.
            String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + recipeID + "/information?includeNutrition=false";
            Log.d("URL", URL);
            //String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=" + name + "&limitLicense=false&number=50&ranking=1";
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
            //String title = getIntent().getExtras().getString("titleDT"), image = getIntent().getExtras().getString("imageDT");
            //int likes = getIntent().getExtras().getInt("likesDT"), recipeID = getIntent().getExtras().getInt("idDT");
            JSONObject JO;
            String title, image;
            int ingredientsLeft, ingredientsUsed, ingredients, count = 0;
            String answer = response.getBody().toString();
            Log.d("Answer", answer);
            /*try {

                //PARSING RECIPES
                JO = new JSONObject(answer);

                    recipeID = JO.getInt("id");
                    //Log.d("RecipeID", Integer.toString(recipeID));
                    title = JO.getString("title");

                    image = JO.getString("image");
                    ingredientsLeft = JO.getInt("missedIngredientCount");
                    ingredientsUsed = JO.getInt("usedIngredientCount");
                    ingredients = ingredientsLeft + ingredientsUsed;
                    recipes = new Recipes(recipeID, title, likes, image, ingredients);
                    count++;
                    Log.d("Ran", "Parsed!");


            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Didn't run", "JSOException");
            }*/

            Intent intent = new Intent(getApplicationContext(), SimilarRecipesBuilder.class);
            intent.putExtra("json_dataDT", answer);
            intent.putExtra("IDforSim", recipeID);
            Log.d("RecipeID in Bookmark", ""+recipeID);
            intent.putExtra("titleDT", name);
            intent.putExtra("likesDT", likes);
            intent.putExtra("imageDT", imageurl);
            startActivity(intent);
            overridePendingTransition(0, 0);//NO ACTIVITY ANIMATION
            finish();
        }

    }

}
