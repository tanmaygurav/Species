package com.ruia.species;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        try {

            String s= model.getSpecimen_image();
            Log.d("Adapter", "onBindViewHolder: substring"+s.substring(0,24));
            if (s.substring(0,24).equals("https://drive.google.com")){
                String[] p=s.split("/");
                //Create the new image link
                String imageLink="https://drive.google.com/uc?export=download&id="+p[5];
                Picasso.get().load(imageLink).into(holder.specimenImage);
                Log.d("Adapter", "onBindViewHolder: Image Set");
            }else {
                Picasso.get().load(model.getSpecimen_image()).into(holder.specimenImage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
//

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SpecimenDetails.class);
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
