package com.example.amk.magedecuisine;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteConstraintException;

/**
 * Created by Rex on 3/9/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME= "recipes.db";
    private static final String DATABASE_INGREDIENT_NAME= "ingredients.db";
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_INGREDIENT = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "_recipename";
    public static final String COLUMN_INGREDIENT = "_ingredientname";
    public static final String COLUMN_SEARCHCODE = "_searchcode";
    public static final String COLUMN_IMAGE = "_image";
    public static final String COLUMN_LIKES = "_likes";
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_RECIPES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPENAME + " TEXT, " +
                COLUMN_SEARCHCODE + " INTEGER, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_LIKES + " INTEGER "  +
                ");";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_INGREDIENT + "(" +
                COLUMN_INGREDIENT + " TEXT " + ");";
        db.execSQL(query2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        onCreate(db);
    }

    //Add a new row to database
    public void addRecipe(Recipe recipe)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, recipe.get_recipename());
        values.put(COLUMN_SEARCHCODE, recipe.get_searchcode());
        Log.d("DB", recipe.get_searchcode() +"");
        values.put(COLUMN_IMAGE, recipe.getImage());
        values.put(COLUMN_LIKES, recipe.get_likes());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public boolean checkRecipeStored(int searchcode)
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_SEARCHCODE +"=\"" + searchcode + "\"";

        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_ingredientname");

        if (recordSet.moveToFirst())
        {
            return true;
        }

        return false;
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


    public void deleteRecipe(int searchcode)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_SEARCHCODE +"=\"" + searchcode + "\"" );
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
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_searchcode"));
                dbString += " ";
                dbString += recordSet.getString(recordSet.getColumnIndex("_image"));
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

    public ArrayList<String> ingToList()
    {
        ArrayList<String> dbStringAr = new ArrayList<String>();
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
                String dbString = recordSet.getString(recordSet.getColumnIndex("_ingredientname"));
                dbStringAr.add(dbString);
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbStringAr;
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

    public ArrayList<Recipe> getBookmarks()
    {
        String recipename;
        int searchcode, likes;
        String image;
        ArrayList<Recipe> result = new ArrayList<Recipe>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE 1";
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        recordSet.getColumnIndex("_recipename");
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("_recipename")) != null) {
                recipename = recordSet.getString(recordSet.getColumnIndex("_recipename"));
                searchcode = recordSet.getInt(recordSet.getColumnIndex("_searchcode"));
                image = recordSet.getString(recordSet.getColumnIndex("_image"));
                likes = recordSet.getInt(recordSet.getColumnIndex("_likes"));
                Recipe recipe = new Recipe(recipename, searchcode, image, likes);
                Log.d("DB recipe", recipe.get_searchcode() + "");
                result.add(recipe);
            }
            recordSet.moveToNext();
        }
        db.close();
        return result;
    }


}

