package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(APICall());
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

    }

    public static String APICall() {
        String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/9266/information?amount=100&unit=gram";
        String APIKey = "KgebgXWQeHmshgowAPA7lmc3utfAp1Vu0jyjsnN2rSrkXexgCY";

        // the request
        try {
            HttpResponse<String> response = Unirest.get(URL)
                    .header("X-Mashape-Key", APIKey)
                    .header("Accept", "application/json")
                    .asString();
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "empty";
    }

//    public void sendMessage(View view) {
//
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//
//        TextView txtView = (TextView) findViewById(R.id.textView1);
//        txtView.setText(message);
//
//        new CallMashapeAsync().execute(message);
//    }
//
//    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {
//
//        protected HttpResponse<JsonNode> doInBackground(String... msg) {
//
//            HttpResponse<JsonNode> request = null;
//            try {
//                request = Unirest.get("https://webknox-question-answering.p.mashape.com/questions/answers?question=" + msg[0] + "&answerLookup=true&answerSearch=true")
//                        .header("X-Mashape-Authorization", "KgebgXWQeHmshgowAPA7lmc3utfAp1Vu0jyjsnN2rSrkXexgCY")
//                        .asJson();
//            } catch (UnirestException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            return request;
//        }
//
//        protected void onProgressUpdate(Integer...integers) {
//        }
//
//        protected void onPostExecute(HttpResponse<JsonNode> response) {
//            String answer = response.getBody().toString();
//            TextView txtView = (TextView) findViewById(R.id.textView1);
//            txtView.setText(answer);
//        }
//    }
}
