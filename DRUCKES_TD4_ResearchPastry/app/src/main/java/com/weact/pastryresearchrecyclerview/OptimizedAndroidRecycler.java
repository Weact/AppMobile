package com.weact.pastryresearchrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptimizedAndroidRecycler extends RecyclerView.Adapter<OptimizedAndroidRecycler.ViewHolder> {
    private ArrayList<Food> FoodList;
    //private String[] mDataSet; //Déclarer votre tableau de données

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView foodName;
        public TextView foodPrice;
        public ImageView foodImage;

        public ViewHolder(View v) {
            super(v);
            foodName = v.findViewById(R.id.foodName);
            foodPrice = v.findViewById(R.id.foodPrice);
            foodImage = v.findViewById(R.id.foodImage);
        }
    }

    public OptimizedAndroidRecycler(ArrayList<Food> myPlanetList) {
        FoodList = myPlanetList;
    }

    @Override
    public OptimizedAndroidRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foodmodel, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.foodName.setText(FoodList.get(position).getName());
        holder.foodPrice.setText(String.format("%s.00€",String.valueOf(FoodList.get(position).getPrice())));
        holder.foodImage.setBackgroundResource(FoodList.get(position).getPictureId());
    }


    @Override
    public int getItemCount() {
        return FoodList.size();
    }
}