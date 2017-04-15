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
 * Created by sukhdipsingh on 4/14/17.
 */

public class IngredientsAdapter  extends ArrayAdapter{
    ArrayList<IngredientList> ingList = new ArrayList<IngredientList>();

    public IngredientsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(IngredientList object)
    {
        super.add(object);
        ingList.add(object);
    }

    @Override
    public int getCount()
    {
        return ingList.size();
    }

    @Override
    public IngredientList getItem(int position)
    {
        return ingList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        IngredientsHolder ingredientsHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.ingredient_layout, parent, false);
            ingredientsHolder = new IngredientsHolder();
            ingredientsHolder.ingAmount = (TextView) row.findViewById(R.id.ingAmount);
            ingredientsHolder.ingImage = (ImageView) row.findViewById(R.id.ingImage);
            row.setTag(ingredientsHolder);
        }

        else
        {
            ingredientsHolder = (IngredientsHolder) row.getTag();
        }

        IngredientList ingredientList = (IngredientList) this.getItem(position);
        Picasso.with(this.getContext()).load(ingredientList.getImage()).resize(100,50).into(ingredientsHolder.ingImage);
        ingredientsHolder.ingAmount.setText(ingredientList.getAmount());
        return row;
    }

    static class IngredientsHolder
    {
        TextView ingAmount;
        ImageView ingImage;
    }

}
