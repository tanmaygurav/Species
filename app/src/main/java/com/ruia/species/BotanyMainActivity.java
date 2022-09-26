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

public class BotanyMainActivity extends AppCompatActivity {
    String urlBT="https://script.googleusercontent.com/macros/echo?user_content_key=0UTF_YlcTosLx4LvfI8Y-jqHq5uNMxLNUlQo50ys0a-mOydPhE-vOmVsK862DsTgZMbwXQbRHYRnRoVcIFVQi-SCXx18CLOfm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnPAg68g5_LIVhOFliTZOHp_Pe53hTzepE9HKrIBVyWuXk3JRqI14l7eeF2SS8MUv_KlAO8UtLHErd1Nu36CMHRTFHgsModOtfw&lib=Mjs4oHSyTOvACHWjwQDMgGW3CjCSjDOnU";
    private static final String TAG = "BotanyMainActivity";
    String HeaderTxt="All Specimens",phylum;
    private ArrayList<SpecimenModel> specimenModelArrayList;
    String commonNameTxt, sciNameTxt,imageTxt,url;
    ProgressDialog loading;
    OkHttpClient client = new OkHttpClient();


    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;

    RecyclerView projectRV;

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
                BSpecimenAdapter specimenAdapter1 = new BSpecimenAdapter(getApplicationContext(),specimenModelArrayList);
                projectRV.setAdapter(specimenAdapter1);
            }
        }, delay);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botany_main);

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
        if (HeaderTxt.equals("Botany Specimens")) {
            getSpecimensB(urlBT);
        } else if ((HeaderTxt.substring(0,2).equals("CB"))){
            getCupboardSpecimens(HeaderTxt);

        }else Toast.makeText(getApplicationContext(),"Error in Header",Toast.LENGTH_SHORT).show();
    }

    private void getCupboardSpecimens(String headerTxt) {

        specimenModelArrayList = new ArrayList<>();
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
            Log.d(TAG, "run: Thread running");
            try {

                Request request = new Request.Builder().url(urlBT).build();
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
                        imageTxt=object.getString("Morphology");
                        String locationTxt = object.getString("Location");
                        Log.d(TAG, "run: location"+locationTxt);
                        if (headerTxt.equals(locationTxt.substring(0,5))){
                            Log.d(TAG, "getCupboardSpecimens: Model Added"+sciNameTxt);
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

    private void getSpecimensB(String urlBT) {

        specimenModelArrayList = new ArrayList<>();
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
            Log.d(TAG, "run: Thread running");
            try {

                Request request = new Request.Builder().url(urlBT).build();
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
                        imageTxt=object.getString("Morphology");
                        Log.d(TAG, "run: SciNames"+sciNameTxt);
                        SpecimenModel model = new SpecimenModel(commonNameTxt,sciNameTxt);
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
        BSpecimenAdapter specimenAdapter1 = new BSpecimenAdapter(getApplicationContext(),specimenModelArrayList);
        projectRV.setAdapter(specimenAdapter1);
    }
}