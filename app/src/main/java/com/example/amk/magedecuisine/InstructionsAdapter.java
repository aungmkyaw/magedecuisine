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

public class InstructionsAdapter extends ArrayAdapter{
    ArrayList<RecipeInstructions> instructList = new ArrayList<RecipeInstructions>();

    public InstructionsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(RecipeInstructions object)
    {
        super.add(object);
        instructList.add(object);
    }

    @Override
    public int getCount()
    {
        return instructList.size();
    }

    @Override
    public RecipeInstructions getItem(int position)
    {
        return instructList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        InstructionsHolder instructionsHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.instructions_layout, parent, false);
            instructionsHolder = new InstructionsHolder();
            instructionsHolder.instructNum = (TextView) row.findViewById(R.id.number);
            instructionsHolder.instructStep = (TextView) row.findViewById(R.id.step);
            instructionsHolder.equipName = (TextView) row.findViewById(R.id.equipname);
            instructionsHolder.equipPic = (ImageView) row.findViewById(R.id.equipimage);

            row.setTag(instructionsHolder);
        }

        else
        {
            instructionsHolder = (InstructionsHolder) row.getTag();
        }

        RecipeInstructions recipeInstructions = (RecipeInstructions) this.getItem(position);
        Picasso.with(this.getContext()).load(recipeInstructions.getEquipmentImage()).resize(100,100).into(instructionsHolder.equipPic);
        instructionsHolder.instructNum.setText(Integer.toString(recipeInstructions.getNumber()));
        instructionsHolder.instructStep.setText(recipeInstructions.getStep());
        instructionsHolder.equipName.setText(recipeInstructions.getEquipmentName());

        return row;
    }

    static class InstructionsHolder
    {
        TextView instructNum, instructStep, equipName;
        ImageView equipPic;
    }

}
