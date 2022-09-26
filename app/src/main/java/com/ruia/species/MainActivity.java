package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String urlZT = "https://script.googleusercontent.com/macros/echo?user_content_key=0J5tuct4yFB2g6fEWdj_JEXbJ5hHLoQZflDRM1U8g_iii8fS6Nd9Sb810E0MK4pkEtXONm4T19c-aMVlYFHY62ILEPD0aHIAm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnISMKmT4sc-oYr2XF2gzS2kVzK3LJnQf1PUbGcvJ9F1imRpjEuoH3KBIxBXgLlSBCu9v9620l5oJVMnCcIoHKO2DwEENJfcyXNz9Jw9Md8uu&lib=MafhO8DCDQWgRY--MA9fA4s4nyNeH4Rra";
    String urlBTI="https://script.googleusercontent.com/macros/echo?user_content_key=gG09FP78K6IKsfRurHG8g4Tn4pkMdL0uGGecLs4r1JNeqQ273-qVcRalTWgBHCNNO3O5AwKVXDleM9uEtU5JnnZw4fLl5M-Dm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnAiK9an07yBTDI2U3irYrpJln3BNZCLekMpgVe8IXUPmCSSIBXfHEuFZuBXkbjDGNCGE-vUmY77T67zS71hMjiJj1CpPWk6Hztz9Jw9Md8uu&lib=MmAOE02fQ8jvJJw3uyo_1hs4nyNeH4Rra";

    private static final String TAG = "MainActivity";

    private ArrayList<SpecimenModel> specimenModelArrayList;
    String HeaderTxt="All Specimens",phylum;
    ProgressDialog loading;
    OkHttpClient client = new OkHttpClient();
    String commonNameTxt, sciNameTxt,imageTxt,url;
    RecyclerView projectRV;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;
    String AD;

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                Log.d(TAG, "onTick: seconds remaining: " + l / 1000);
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                Log.d(TAG, "onFinish: Callback removed");
            }
        }.start();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Log.d(TAG, "run: Refresh Triggered");
                SpecimenAdapter specimenAdapter = new SpecimenAdapter(getApplicationContext(),specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter);
            }
        }, delay);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectRV = findViewById(R.id.idRVProject);
        TextView header = findViewById(R.id.idSpecimenListHeader);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            HeaderTxt = extras.getString("CupboardNo");
        Log.d(TAG, "onCreate: header"+HeaderTxt);
        phylum = extras.getString("Phylum");

        header.setText(HeaderTxt);
//      select url for bio or zoo

        if (HeaderTxt.equals("Zoology Specimens")) {
            getSpecimensZ(urlZT);
        } else if ((HeaderTxt.substring(0,2).equals("CZ"))){
            getCupboardSpecimens(HeaderTxt);

        }else Toast.makeText(getApplicationContext(),"Error in Header",Toast.LENGTH_SHORT).show();
    }

    private void getCupboardSpecimens(String headerTxt) {
        specimenModelArrayList = new ArrayList<>();
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
            Log.d(TAG, "run: Thread running");
            try {

                Request request = new Request.Builder().url(urlZT).build();
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
                        String locationTxt = object.getString("Location");
                        Log.d(TAG, "run: location"+locationTxt);
                        if (headerTxt.equals(locationTxt.substring(0,5))){
                            Log.d(TAG, "getCupboardSpecimens: Model Added"+sciNameTxt+" "+locationTxt);
                            SpecimenModel model = new SpecimenModel(commonNameTxt,sciNameTxt);
                            specimenModelArrayList.add(model);
                        }
                    }
                    if (loading.isShowing()) {
                        loading.dismiss();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    loading = ProgressDialog.show(this,"Error",e.getMessage(),false,true);
                }
            }catch (Exception e){
                e.printStackTrace();
//                loading = ProgressDialog.show(this,"Error",e.getMessage(),false,true);
            }

        });
        thread.start();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRV.setLayoutManager(linearLayoutManager);
        BSpecimenAdapter specimenAdapter1 = new BSpecimenAdapter(getApplicationContext(),specimenModelArrayList);
        projectRV.setAdapter(specimenAdapter1);
    }


    private void getSpecimensZ(String url) {

        AD="Zoology";
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
                    loading = ProgressDialog.show(this,"Error",e.getMessage(),false,true);
                }
            }catch (Exception e){
                e.printStackTrace();
//                loading = ProgressDialog.show(this,"Error",e.getMessage(),false,true);
            }

        });
        thread.start();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRV.setLayoutManager(linearLayoutManager);
        SpecimenAdapter specimenAdapter = new SpecimenAdapter(getApplicationContext(),specimenModelArrayList);
        projectRV.setAdapter(specimenAdapter);
    }

}
