package com.example.amk.magedecuisine;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;
/**
 * Created by Rex on 3/9/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "recipes.db";
    private static final String DATABASE_INGREDIENT_NAME= "ingredients.db";
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_INGREDIENT = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "_recipename";
    public static final String COLUMN_INGREDIENT = "_ingredientname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_RECIPES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPENAME + " TEXT " +
                ");";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_INGREDIENT + "(" +
                COLUMN_INGREDIENT + " TEXT " + ");";
        db.execSQL(query2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    //Add a new row to database
    public void addRecipe(Recipe recipe)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, recipe.get_recipename());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    //Add a new row for Ingredients
    public void addIngredient(Ingredient ingredient)
    {
        ContentValues names = new ContentValues();
        names.put(COLUMN_INGREDIENT, ingredient.get_ingredient());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INGREDIENT, null, names);
        db.close();
    }


    public void deleteRecipe(String recipeName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPENAME +"=\"" + recipeName + "\"" );
    }

    public void deleteIngredients(String recipeName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_INGREDIENT + " WHERE " + COLUMN_INGREDIENT +"=\"" + recipeName + "\"" );
    }

    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE 1";

        //Cursor point to the location in yoru result
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        //Position after the last row means the end of the results

        //Log.d("myTag", "This is my message " + recordSet.getColumnIndex("_recipename"));
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_recipename"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public String ingredientToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENT + " WHERE 1";

        //Cursor point to the location in yoru result
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_ingredientname");
        //Position after the last row means the end of the results

        //Log.d("myTag", "This is my message " + recordSet.getColumnIndex("_recipename"));
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_ingredientname")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_ingredientname"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public String ingSearchString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENT + " WHERE 1";

        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_ingredientname");
        //Position after the last row means the end of the results

        //Log.d("myTag", "This is my message " + recordSet.getColumnIndex("_recipename"));
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_ingredientname")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("_ingredientname"));
                dbString += ",";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }




}

