package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ModelCheck extends AppCompatActivity {
    private static final String TAG = "ModelCheck";
    TextView modelHeader;
    RecyclerView modelRV;
    String selectedSpecimen;
    int modelID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_check);

        modelHeader=findViewById(R.id.idModelCheckHeader);
        modelRV=findViewById(R.id.idModelRV);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            selectedSpecimen= extras.getString("SciName");
            Log.d(TAG, "onCreate: SciName"+selectedSpecimen);
        }

        if (checkModel(selectedSpecimen)) {
            getModelID();
        }else {
            modelHeader.setText("3D view of this Specimen is currently not available Please Try Another Specimen");
        }
        if (modelID!=0){
            Intent intent1 = new Intent(getApplicationContext(),AR.class);
            intent1.putExtra("model",modelID);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Error in Loading Model",Toast.LENGTH_SHORT).show();
        }
    }

    private void getModelID() {
        if (selectedSpecimen.equals("Hippocampus")) modelID=R.raw.hippocampus;
        if (selectedSpecimen.equals("Scoliodon lacticaudus")) modelID=R.raw.scoliodon_lacticaudus;
        if (selectedSpecimen.equals("Coptotettix korbensis")) modelID=R.raw.coptotettix_korbensis;
        if (selectedSpecimen.equals("Mantis religiosa")) modelID=R.raw.mantis_religiosa;
        if (selectedSpecimen.equals("Apis mellifera")) modelID=R.raw.apis_mellifera;
        if (selectedSpecimen.equals("Scolopendra subspinipes")) modelID=R.raw.scolopendra_subspinipes;
        if (selectedSpecimen.equals("Carcharodon carcharias")) modelID=R.raw.carcharodon_carcharias;
    }

    private boolean checkModel(String selectedSpecimen) {
        ArrayList<String> avModelList = new ArrayList<>();
        avModelList.add("Hippocampus");
        avModelList.add("Scoliodon lacticaudus");
        avModelList.add("Coptotettix korbensis");
        avModelList.add("Mantis religiosa");
        avModelList.add("Apis mellifera");
        avModelList.add("Scolopendra subspinipes");
        avModelList.add("Carcharodon carcharias");

//        Add more models here

        return avModelList.contains(selectedSpecimen);
    }
}