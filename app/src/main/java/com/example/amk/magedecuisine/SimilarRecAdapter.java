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
 * Created by sukhdipsingh on 4/16/17.
 */

public class SimilarRecAdapter extends ArrayAdapter {
    ArrayList<SimilarRecipes> simList = new ArrayList<SimilarRecipes>();

    public SimilarRecAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(SimilarRecipes object)
    {
        super.add(object);
        simList.add(object);
    }

    @Override
    public int getCount()
    {
        return simList.size();
    }

    @Override
    public SimilarRecipes getItem(int position)
    {
        return simList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        SimRecipeHolder simRecipeHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.simrecipes_layout, parent, false);
            simRecipeHolder = new SimRecipeHolder();
            simRecipeHolder.simTitle = (TextView) row.findViewById(R.id.similarTitle);
            simRecipeHolder.simTime = (TextView) row.findViewById(R.id.similarTime);
            simRecipeHolder.simImage = (ImageView) row.findViewById(R.id.similarImage);
            row.setTag(simRecipeHolder);
        }

        else
        {
            simRecipeHolder = (SimRecipeHolder) row.getTag();
        }

        SimilarRecipes simRecipes = (SimilarRecipes) this.getItem(position);
        Picasso.with(this.getContext()).load(simRecipes.getImage()).resize(100,50).into(simRecipeHolder.simImage);
        simRecipeHolder.simTitle.setText(simRecipes.getTitle());
        simRecipeHolder.simTime.setText(Integer.toString(simRecipes.getCookTime()));

        return row;
    }

    static class SimRecipeHolder
    {
        TextView simTitle, simTime;
        ImageView simImage;
    }
}
