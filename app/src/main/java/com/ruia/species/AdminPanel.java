package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        RecyclerView projectRV;
        ArrayList<SpecimenModel> specimenModelArrayList;

        projectRV = findViewById(R.id.idRVProject);

        specimenModelArrayList = new ArrayList<>();
        specimenModelArrayList.add(new SpecimenModel("Rabbit","1",R.drawable.rabbit,R.drawable.redball));
        specimenModelArrayList.add(new SpecimenModel("Bee","1",R.drawable.bee,R.drawable.yellowball));
        specimenModelArrayList.add(new SpecimenModel("Jellyfish","1",R.drawable.jellyfish,R.drawable.orangeball));
        specimenModelArrayList.add(new SpecimenModel("Komodo Dragon","1",R.drawable.komododragon,R.drawable.greenball));
        specimenModelArrayList.add(new SpecimenModel("Ulysses Butterfly","2",R.drawable.ulyssesbutterfly,R.drawable.orangeball));
        specimenModelArrayList.add(new SpecimenModel("Monarch Butterfly","2",R.drawable.monrachbutterfly,R.drawable.orangeball));
        specimenModelArrayList.add(new SpecimenModel("Octopus","2",R.drawable.ocutopus,R.drawable.greenball));
        specimenModelArrayList.add(new SpecimenModel("SeaHorse","2",R.drawable.seahorse,R.drawable.greenball));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRV.setLayoutManager(linearLayoutManager);

        AdminSpecimenAdapter adminSpecimenAdapter = new AdminSpecimenAdapter(this,specimenModelArrayList);
        projectRV.setAdapter(adminSpecimenAdapter);
    }
}