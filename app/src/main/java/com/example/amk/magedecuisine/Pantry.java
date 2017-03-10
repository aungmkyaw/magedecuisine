package com.example.amk.magedecuisine;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Pantry extends ListActivity {

    //List of Array strings
    //List of items
    ArrayList<String> listItems = new ArrayList<String>();

    //Handles the Data of listview
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_pantry);
        final EditText entItem = (EditText) findViewById(R.id.entItem);
        final Button addBtn = (Button) findViewById(R.id.addBtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);

        //Handles dynamic insertion of the string
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addItem = entItem.getText().toString();
                listItems.add(addItem);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
