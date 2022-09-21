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

public class BSpecimenAdapter extends RecyclerView.Adapter<BSpecimenAdapter.Viewholder>{
    private Context context;
    private ArrayList<SpecimenModel> specimenModelArrayList;

    public BSpecimenAdapter(Context context, ArrayList<SpecimenModel> specimenModelArrayList) {
        this.context = context;
        this.specimenModelArrayList = specimenModelArrayList;
    }

    @NonNull
    @Override
    public BSpecimenAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specimen_card_no_image, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BSpecimenAdapter.Viewholder holder, int position) {
        SpecimenModel model = specimenModelArrayList.get(position);
        holder.commonNameTV.setText(model.getCommon_name());
        holder.ScientificNameTV.setText(model.getScientific_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Botany_Specimen_Details.class);
                intent.putExtra("projectName",model.getCommon_name());
                intent.putExtra("SciName",model.getScientific_name());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specimenModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView commonNameTV,ScientificNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            commonNameTV=itemView.findViewById(R.id.idSpecimenCommonName1);
            ScientificNameTV=itemView.findViewById(R.id.idSpecimenSciName1);
        }
    }
}
