package com.ruia.species;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpecimenAdapter extends RecyclerView.Adapter<SpecimenAdapter.Viewholder> {
    private Context context;
    private ArrayList<SpecimenModel> specimenModelArrayList;

    public SpecimenAdapter(Context context, ArrayList<SpecimenModel> specimenModelArrayList) {
        this.context = context;
        this.specimenModelArrayList = specimenModelArrayList;
    }

    @NonNull
    @Override
    public SpecimenAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specimen_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SpecimenModel model = specimenModelArrayList.get(position);
        holder.commonNameTV.setText(model.getCommon_name());
        holder.ScientificNameTV.setText(model.getScientific_name());
        holder.specimenImage.setImageURI(Uri.parse(model.getSpecimen_image()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SpecimenDetails.class);
                intent.putExtra("projectName",model.getCommon_name());
                intent.putExtra("SciName",model.getScientific_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specimenModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView specimenImage,formaldehydeIndicator;
        private TextView commonNameTV,ScientificNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            specimenImage=itemView.findViewById(R.id.idSpecimenImage);
            commonNameTV=itemView.findViewById(R.id.idSpecimenCommonName);
            ScientificNameTV=itemView.findViewById(R.id.idSpecimenSciName);
        }
    }
}
