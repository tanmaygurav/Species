package com.ruia.species;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter <HomeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HomeModel> listModels;

    public HomeAdapter(Context context, ArrayList<HomeModel> listModels) {
        this.context = context;
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.homegrid_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel myListModel = listModels.get(position);

        holder.imageView.setImageResource(myListModel.getImgId());
        holder.header.setText(myListModel.getHeader());
        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(context,AR.class);
            intent.putExtra("name",myListModel.getHeader());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView header;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.idIVImage);
            this.header = itemView.findViewById(R.id.idHomeHeader);
        }
    }
}
