package com.example.amk.magedecuisine;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Pantry extends AppCompatActivity {

    MyDBHandler dbHandler;
    TextView pantryView;
    EditText entItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        pantryView = (TextView) findViewById(R.id.pantryView);
        entItem = (EditText) findViewById(R.id.entItem);
        final Button addBtn = (Button) findViewById(R.id.addBtn);
        final Button rmvBtn = (Button) findViewById(R.id.rmvBtn);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public void addBtnClicked(View view)
    {
        Ingredient ingredient = new Ingredient(entItem.getText().toString().toString());
        dbHandler.addIngredient(ingredient);
        printDatabase();
    }

    public void rmvBtnClicked(View view)
    {
        String inputText = entItem.getText().toString();
        dbHandler.deleteRecipe(inputText);
        printDatabase();
    }

    public void printDatabase()
    {
        String dbString = dbHandler.ingredientToString();
        pantryView.setText(dbString);
        entItem.setText("");
    }


}
