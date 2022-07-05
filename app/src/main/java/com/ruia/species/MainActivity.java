package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectRV;
    private ArrayList<ProjectModel> projectModelArrayList;
    private ArrayList<SpecimenModel> specimenModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Hard coded project list




//        Dynamic project list code
        projectRV = findViewById(R.id.idRVProject);

        // here we have created new array list and added data to it.
//        projectModelArrayList = new ArrayList<>();
//        projectModelArrayList.add(new ProjectModel("Monitor Lizard", R.drawable.monitor_lizard, R.drawable.monitor_lizard));
//        projectModelArrayList.add(new ProjectModel("Demo", R.drawable.gfg_gold_text_stand_2, R.drawable.gfg_gold_text_stand_2));


        // we are initializing our adapter class and passing our arraylist to it.
//        ProjectAdapter projectAdapter = new ProjectAdapter(this, projectModelArrayList);

//        Cupboard 1 list
        specimenModelArrayList = new ArrayList<>();
        specimenModelArrayList.add(new SpecimenModel("Rabbit","Oryctolagus cuniculus",R.drawable.rabbit));

        // we are initializing our adapter class and passing our arraylist to it.
        SpecimenAdapter specimenAdapter = new SpecimenAdapter(this,specimenModelArrayList);

        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        projectRV.setLayoutManager(linearLayoutManager);
        projectRV.setAdapter(specimenAdapter);
    }

}