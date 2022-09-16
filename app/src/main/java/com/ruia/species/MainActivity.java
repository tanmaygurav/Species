package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String url = "https://script.googleusercontent.com/macros/echo?user_content_key=zUjv73MQztNx4Tn3DruKNJ34W6cbGG65mJtueACmB0Kq_iu7NdHOb9AZMjfZzmN_OVEdCNs4ROXGfvZc1VIRbV11w2t6yrEYm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnNFki3g1hL3IUOyN12gWNmVBz2p2TKXc9Xuv3WMU4Wnz1LsR9TGQAsYlZRA1hBWNPk8_MUtqX4Zwp4r-nmUQb9Tx90esvHgeXA&lib=MbgrK457YjWEMRj1RpcABUG3CjCSjDOnU";
    private static final String TAG = "MainActivity";

    private ArrayList<SpecimenModel> specimenModelArrayList;
    String HeaderTxt="All Specimens";
    ProgressDialog loading;
    OkHttpClient client = new OkHttpClient();
    String commonNameTxt, sciNameTxt,imageTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView projectRV = findViewById(R.id.idRVProject);
        TextView header = findViewById(R.id.idSpecimenListHeader);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            HeaderTxt = extras.getString("Cupboard");

        header.setText(HeaderTxt);

//Displaying all  specimens
        specimenModelArrayList = new ArrayList<>();
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
            Log.d(TAG, "run: Thread running");
            try {

                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    String jsonData = Objects.requireNonNull(response.body()).string();
//                        Log.d(TAG, "run: jsonData"+jsonData);
                    JSONObject Jobject = new JSONObject(jsonData);
                    JSONArray Jarray = Jobject.getJSONArray("details");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        commonNameTxt=object.getString("Common Name");
                        sciNameTxt= object.getString("Scientific Name");
                        imageTxt=object.getString("Image");
                        Log.d(TAG, "run: SciNames"+sciNameTxt);
                        SpecimenModel model = new SpecimenModel(commonNameTxt,sciNameTxt,imageTxt);
                        specimenModelArrayList.add(model);
                    }
                    if (loading.isShowing()) {
                        loading.dismiss();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        thread.start();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRV.setLayoutManager(linearLayoutManager);
//        SpecimenAdapter specimenAdapter = new SpecimenAdapter(this,specimenModelArrayList);
//        projectRV.setAdapter(specimenAdapter);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SpecimenAdapter specimenAdapter = new SpecimenAdapter(getApplicationContext(),specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter);
            }
        }, 5000);




        /*switch (HeaderTxt){
            case "All Specimens":
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
        }*/



    }

}
//        Dynamic project list code
// here we have created new array list and added data to it.
//        projectModelArrayList = new ArrayList<>();
//        projectModelArrayList.add(new ProjectModel("Monitor Lizard", R.drawable.monitor_lizard, R.drawable.monitor_lizard));
//        projectModelArrayList.add(new ProjectModel("Demo", R.drawable.gfg_gold_text_stand_2, R.drawable.gfg_gold_text_stand_2));


// we are initializing our adapter class and passing our arraylist to it.
//        ProjectAdapter projectAdapter = new ProjectAdapter(this, projectModelArrayList);