package com.weact.recyclerviewlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptimizedAndroidRecycler extends RecyclerView.Adapter<OptimizedAndroidRecycler.ViewHolder> {
    private ArrayList<Planet> PlanetList;
    //private String[] mDataSet; //Déclarer votre tableau de données

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView planetName;
        public TextView planetAge;
        public ImageView planetImage;

        public ViewHolder(View v) {
            super(v);
            planetName = v.findViewById(R.id.planetName);
            planetAge = v.findViewById(R.id.planetAge);
            planetImage = v.findViewById(R.id.planetImage);
        }
    }

    public OptimizedAndroidRecycler(ArrayList<Planet> myPlanetList) {
        PlanetList = myPlanetList;
    }

    @Override
    public OptimizedAndroidRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planetelement, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.planetName.setText(PlanetList.get(position).getName());
        holder.planetAge.setText(PlanetList.get(position).getAge());
        holder.planetImage.setBackgroundResource(PlanetList.get(position).getPictureId());
    }


    @Override
    public int getItemCount() {
        return PlanetList.size();
    }
}