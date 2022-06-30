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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Viewholder> {

    private Context context;
    private ArrayList<ProjectModel> projectModelArrayList;

    // Constructor GEN
    public ProjectAdapter(Context context, ArrayList<ProjectModel> projectModelArrayList) {
        this.context = context;
        this.projectModelArrayList = projectModelArrayList;
    }

    @NonNull
    @Override
    public ProjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        ProjectModel model = projectModelArrayList.get(position);
        holder.projectNameTV.setText(model.getProject_name());
        holder.projectImage.setImageResource(model.getProject_image());
        holder.projectLogo.setImageResource(model.getProject_logo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AR.class);
                intent.putExtra("projectName",model.getProject_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private ImageView projectImage, projectLogo;
        private TextView projectNameTV;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            projectImage = itemView.findViewById(R.id.idProjectImage);
            projectNameTV = itemView.findViewById(R.id.idProjectName);
            projectLogo = itemView.findViewById(R.id.idProjectLogo);
        }
    }
}