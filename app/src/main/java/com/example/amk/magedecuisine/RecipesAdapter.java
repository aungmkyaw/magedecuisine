package com.example.amk.magedecuisine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

    @Override
    public void add(Object object)
    {
        super.add(object);
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public Object getItem(int position)
    {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return super.getView(position, convertView, parent);
    }


}
