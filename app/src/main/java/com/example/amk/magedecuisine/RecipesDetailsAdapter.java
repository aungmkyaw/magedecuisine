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
 * Created by sukhdipsingh on 3/29/17.
 */

public class RecipesDetailsAdapter extends ArrayAdapter {
    ArrayList<RecipeDetails> list = new ArrayList<RecipeDetails>();

    public RecipesDetailsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(RecipeDetails object)
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
    public RecipeDetails getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        RecipeDetailsHolder recipeDetailsHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_recipes_detail_builder, parent, false);
            recipeDetailsHolder = new RecipeDetailsHolder();
            recipeDetailsHolder.tx_title = (TextView) row.findViewById(R.id.TitleDT);
            recipeDetailsHolder.tx_likes = (TextView) row.findViewById(R.id.LikesDT);
            recipeDetailsHolder.tx_image = (ImageView) row.findViewById(R.id.imageDT);
            recipeDetailsHolder.tx_cookTime = (TextView) row.findViewById(R.id.CookTimeDT);
            row.setTag(recipeDetailsHolder);
        }
        else
        {
            recipeDetailsHolder = (RecipeDetailsHolder) row.getTag();
        }

        RecipeDetails recipes = (RecipeDetails) this.getItem(position);
        Picasso.with(this.getContext()).load(recipes.getImage()).resize(100,50).into(recipeDetailsHolder.tx_image);
        recipeDetailsHolder.tx_title.setText(recipes.getTitle());
        recipeDetailsHolder.tx_likes.setText(Integer.toString(recipes.getLikes()));
        recipeDetailsHolder.tx_cookTime.setText(Integer.toString(recipes.getCookTime()));

        return row;
    }

    static class RecipeDetailsHolder
    {
        TextView tx_title, tx_likes, tx_cookTime;
        ImageView tx_image;
    }

}