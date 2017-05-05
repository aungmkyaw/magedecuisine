package com.example.amk.magedecuisine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bookmarks extends AppCompatActivity {

    EditText bmInput;
    TextView bookMarkView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        bmInput = (EditText) findViewById(R.id.bmInput);
        bookMarkView = (TextView) findViewById(R.id.bookMarkview);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }



    public void printDatabase()
    {
        String dbString = dbHandler.databaseToString();
        bookMarkView.setText(dbString);
        bmInput.setText("");
    }



}
