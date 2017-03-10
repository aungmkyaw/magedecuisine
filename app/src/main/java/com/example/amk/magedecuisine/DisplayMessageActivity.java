package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
       // APICall();
    }

//    public static void APICall() {
//        String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/9266/information?amount=100&unit=gram";
//        String APIKey = "KgebgXWQeHmshgowAPA7lmc3utfAp1Vu0jyjsnN2rSrkXexgCY";
//
//        // the request
//        try {
//            HttpResponse<JsonNode> response = Unirest.get(URL)
//                    .header("X-Mashape-Key", APIKey)
//                    .header("Accept", "application/json")
//                    .asJson();
//            response.getStatusText();
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
//    }
}
