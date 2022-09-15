package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectRV;
    private ArrayList<ProjectModel> projectModelArrayList;
    private ArrayList<SpecimenModel> specimenModelArrayList;
    String Cupboard=null;
    private TextView cupboardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectRV = findViewById(R.id.idRVProject);
        cupboardNumber= findViewById(R.id.idCupboardNumberHeader);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            Cupboard = extras.getString("Cupboard");

        cupboardNumber.setText(Cupboard.toString());

// Insert old code Here

        // we are initializing our adapter class and passing our arraylist to it.
//        SpecimenAdapter specimenAdapter = new SpecimenAdapter(this,specimenModelArrayList);
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRV.setLayoutManager(linearLayoutManager);
//        projectRV.setAdapter(specimenAdapter);
//        Cupboard 1 list
        switch (Cupboard){
            case "Cupboard":
                specimenModelArrayList = new ArrayList<>();
                specimenModelArrayList.add(new SpecimenModel("Rabbit","Oryctolagus cuniculus",R.drawable.rabbit));
                specimenModelArrayList.add(new SpecimenModel("Bee","Apis mellifera",R.drawable.bee));
                specimenModelArrayList.add(new SpecimenModel("Jellyfish","Scyphozoa",R.drawable.jellyfish));
                specimenModelArrayList.add(new SpecimenModel("Komodo Dragon","Varanus komodoensis",R.drawable.komododragon));
                specimenModelArrayList.add(new SpecimenModel("Ulysses Butterfly","Papilio ulysses",R.drawable.ulyssesbutterfly));
                specimenModelArrayList.add(new SpecimenModel("Monarch Butterfly","Danaus plexippus",R.drawable.monrachbutterfly));
                specimenModelArrayList.add(new SpecimenModel("Octopus","Octopoda",R.drawable.ocutopus));
                specimenModelArrayList.add(new SpecimenModel("SeaHorse","Hippocampus",R.drawable.seahorse));
                SpecimenAdapter specimenAdapter0 = new SpecimenAdapter(this,specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter0);
                break;
            case "Cupboard 1":
                specimenModelArrayList = new ArrayList<>();
                specimenModelArrayList.add(new SpecimenModel("Rabbit","Oryctolagus cuniculus",R.drawable.rabbit));
                specimenModelArrayList.add(new SpecimenModel("Bee","Apis mellifera",R.drawable.bee));
                specimenModelArrayList.add(new SpecimenModel("Jellyfish","Scyphozoa",R.drawable.jellyfish));
                specimenModelArrayList.add(new SpecimenModel("Komodo Dragon","Varanus komodoensis",R.drawable.komododragon));

                SpecimenAdapter specimenAdapter1 = new SpecimenAdapter(this,specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter1);
                break;
            case "Cupboard 2":
                specimenModelArrayList = new ArrayList<>();
                specimenModelArrayList.add(new SpecimenModel("Ulysses Butterfly","Papilio ulysses",R.drawable.ulyssesbutterfly));
                specimenModelArrayList.add(new SpecimenModel("Monarch Butterfly","Danaus plexippus",R.drawable.monrachbutterfly));
                specimenModelArrayList.add(new SpecimenModel("Octopus","Octopoda",R.drawable.ocutopus));
                specimenModelArrayList.add(new SpecimenModel("SeaHorse","Hippocampus",R.drawable.seahorse));

                SpecimenAdapter specimenAdapter2 = new SpecimenAdapter(this,specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter2);
                break;
//  Switch case end here
        }



    }

}
//        Dynamic project list code
// here we have created new array list and added data to it.
//        projectModelArrayList = new ArrayList<>();
//        projectModelArrayList.add(new ProjectModel("Monitor Lizard", R.drawable.monitor_lizard, R.drawable.monitor_lizard));
//        projectModelArrayList.add(new ProjectModel("Demo", R.drawable.gfg_gold_text_stand_2, R.drawable.gfg_gold_text_stand_2));


// we are initializing our adapter class and passing our arraylist to it.
//        ProjectAdapter projectAdapter = new ProjectAdapter(this, projectModelArrayList);