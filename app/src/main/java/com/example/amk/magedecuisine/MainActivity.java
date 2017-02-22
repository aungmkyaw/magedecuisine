package com.example.amk.magedecuisine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.amk.magedecuisine.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** CALLED WHEN USER CLICKS RECIPE BUILDER FUNCTION **/
    public void doSomething(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Button recipeButton = (Button) findViewById(R.id.button16);
        String message = "Senior Project";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
