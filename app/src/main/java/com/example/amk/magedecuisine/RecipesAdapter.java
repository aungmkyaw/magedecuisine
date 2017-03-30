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
 * Created by sukhdipsingh on 3/16/17.
 */

public class RecipesAdapter extends ArrayAdapter{
    ArrayList<Recipes> list = new ArrayList<Recipes>();

    public RecipesAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Recipes object)
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
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        RecipeHolder recipeHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            recipeHolder = new RecipeHolder();
            recipeHolder.tx_title = (TextView) row.findViewById(R.id.tx_title);
            recipeHolder.tx_likes = (TextView) row.findViewById(R.id.tx_likes);
            recipeHolder.tx_image = (ImageView) row.findViewById(R.id.tx_image);
            recipeHolder.tx_ingredientsLeft = (TextView) row.findViewById(R.id.tx_ingredientsLeft);
            row.setTag(recipeHolder);
        }

        else
        {
            recipeHolder = (RecipeHolder) row.getTag();
        }

        Recipes recipes = (Recipes) this.getItem(position);
        Picasso.with(this.getContext()).load(recipes.getImage()).resize(100,50).into(recipeHolder.tx_image);
        recipeHolder.tx_title.setText(recipes.getTitle());
        recipeHolder.tx_likes.setText(Integer.toString(recipes.getLikes()));
        recipeHolder.tx_ingredientsLeft.setText(Integer.toString(recipes.getIngredientsLeft()));

        return row;
    }

    static class RecipeHolder
    {
        TextView tx_title, tx_likes, tx_ingredientsLeft;
        ImageView tx_image;
    }

}
