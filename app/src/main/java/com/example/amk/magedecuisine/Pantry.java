package com.example.amk.magedecuisine;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Pantry extends ListActivity {

    //List of Array strings
    //List of items
    ArrayList<String> listItems = new ArrayList<String>();

    //Handles the Data of listview
    ArrayAdapter<String> adapter;

    //Record # of clicks
    int clickCounter = 0;


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_pantry);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);
    }

    //This handles dynamic insertion ;)
    public void addItems(View v) {
        listItems.add("Clicked : " + clickCounter++ );
        adapter.notifyDataSetChanged();
    }

}
