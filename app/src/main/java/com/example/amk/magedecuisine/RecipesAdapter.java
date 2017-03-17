package com.example.amk.magedecuisine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhdipsingh on 3/16/17.
 */

public class RecipesAdapter extends ArrayAdapter{
    List list = new ArrayList();

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
            recipeHolder.tx_id = (TextView) row.findViewById(R.id.tx_id);
            recipeHolder.tx_title = (TextView) row.findViewById(R.id.tx_title);
            recipeHolder.tx_likes = (TextView) row.findViewById(R.id.tx_likes);
            row.setTag(recipeHolder);
        }

        else
        {
            recipeHolder = (RecipeHolder) row.getTag();
        }

        Recipes recipes = (Recipes) this.getItem(position);
        recipeHolder.tx_id.setText(recipes.getId());
        recipeHolder.tx_title.setText(recipes.getTitle());
        recipeHolder.tx_likes.setText(recipes.getLikes());

        return row;
    }

    static class RecipeHolder
    {
        TextView tx_id, tx_title, tx_likes;
    }

}
