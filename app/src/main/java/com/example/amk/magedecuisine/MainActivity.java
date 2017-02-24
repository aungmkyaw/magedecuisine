package com.example.amk.magedecuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.amk.magedecuisine.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** CALLED WHEN USER CLICKS RECIPE BUILDER BUTTON **/
    public void doSomething(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Button recipeButton = (Button) findViewById(R.id.button16);
        String message = "Senior Project";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** CALLED WHEN USER CLICKS PANTRY BUTTON **/
    public void pantryAction(View view)
    {
        Intent intent = new Intent(this, Pantry.class);
        Button pantryButton = (Button) findViewById(R.id.button14);
        startActivity(intent);
    }

    /** CALLED WHEN USER CLICKS BOOKMARKS BUTTON **/
    public void BookmarksAction(View view)
    {
        Intent intent = new Intent(this, Bookmarks.class);
        Button BookmarksButton = (Button) findViewById(R.id.button15);
        startActivity(intent);
    }

}
