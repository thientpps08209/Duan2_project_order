package com.example.asm_finalfood.Model;

import android.widget.Filter;

import com.example.asm_finalfood.Adapter.AllFoodAdapter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    AllFoodAdapter allFoodAdapter;
    ArrayList<Food> filterList;

    public CustomFilter(ArrayList<Food> filterList, AllFoodAdapter allFoodAdapter)
    {
        this.allFoodAdapter = allFoodAdapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Food> filteredFood=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredFood.add(filterList.get(i));
                }
            }

            results.count=filteredFood.size();
            results.values=filteredFood;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        allFoodAdapter.foods= (ArrayList<Food>) results.values;

        //REFRESH
        allFoodAdapter.notifyDataSetChanged();

    }
}