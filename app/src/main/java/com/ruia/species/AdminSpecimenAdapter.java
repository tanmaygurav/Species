package com.ruia.species;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminSpecimenAdapter extends RecyclerView.Adapter<AdminSpecimenAdapter.Viewholder>{
    private Context context;
    private ArrayList<SpecimenModel> specimenModelArrayList;

    public AdminSpecimenAdapter(Context context, ArrayList<SpecimenModel> specimenModelArrayList) {
        this.context = context;
        this.specimenModelArrayList = specimenModelArrayList;
    }

    @NonNull
    @Override
    public AdminSpecimenAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_specimen_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSpecimenAdapter.Viewholder holder, int position) {
        SpecimenModel model = specimenModelArrayList.get(position);
        holder.commonNameTV.setText(model.getCommon_name());
        holder.cupboardNumber.setText(model.getCupboardNumber());
        holder.specimenImage.setImageURI(Uri.parse(model.getSpecimen_image()));
        holder.formaldehydeIndicator.setImageResource(model.getFormaldehydeChange());
    }

    @Override
    public int getItemCount() {
        return specimenModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView specimenImage,formaldehydeIndicator;
        private TextView commonNameTV,cupboardNumber;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            specimenImage=itemView.findViewById(R.id.idSpecimenImage);
            commonNameTV=itemView.findViewById(R.id.idSpecimenCommonName);
            cupboardNumber=itemView.findViewById(R.id.idCupboardNumber);
            formaldehydeIndicator=itemView.findViewById(R.id.idFormaldehydeIndicator);
        }
    }
}
