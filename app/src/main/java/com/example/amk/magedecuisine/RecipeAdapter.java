package com.example.amk.magedecuisine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rex on 5/4/2017.
 */

public class RecipeAdapter extends ArrayAdapter {
    ArrayList<Recipe> list = new ArrayList<Recipe>();

    public RecipeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Recipe object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Recipe getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        RecipeAdapter.RecipeHolder recipeHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            recipeHolder = new RecipeAdapter.RecipeHolder();
            recipeHolder.tx_title = (TextView) row.findViewById(R.id.tx_title);
            recipeHolder.tx_likes = (TextView) row.findViewById(R.id.tx_likes);
            recipeHolder.tx_image = (ImageView) row.findViewById(R.id.tx_image);
            recipeHolder.tx_ingredients = (TextView) row.findViewById(R.id.tx_ingredients);
            row.setTag(recipeHolder);
        }

        else
        {
            recipeHolder = (RecipeAdapter.RecipeHolder) row.getTag();
        }

        Recipe recipes = (Recipe) this.getItem(position);
        Picasso.with(this.getContext()).load(recipes.getImage()).resize(150,150).into(recipeHolder.tx_image);
        recipeHolder.tx_title.setText(recipes.get_recipename());
        recipeHolder.tx_likes.setText(Integer.toString(recipes.get_likes()));
        recipeHolder.tx_ingredients.setText(Integer.toString(recipes.get_ingredent()));

        return row;
    }

    static class RecipeHolder
    {
        TextView tx_title, tx_likes, tx_ingredients;
        ImageView tx_image;
    }
}
