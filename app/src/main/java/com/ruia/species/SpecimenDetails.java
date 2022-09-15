package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpecimenDetails extends AppCompatActivity {
    private static final String TAG = "SpecimenDetails";
    TextView commonName, sciName, kingdom, subKingdom, infraKingdom, grade, division,
            subDivision, phylum, group, subPhylum, superClass, class1, subClass,
            infraClass, superOrder, order, subOrder, infraOrder, family, genus, description, ref;
    Button audio, extLinks, view3D;
    String selectedSpecimen;
    ProgressDialog loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specimen_details);

        commonName=findViewById(R.id.idSpecimenCommonName);
        sciName=findViewById(R.id.idSpecimenSciName);
        kingdom=findViewById(R.id.idKingdom);
        subKingdom=findViewById(R.id.idSubKingdom);
        infraKingdom=findViewById(R.id.idInfraKingdom);
        grade=findViewById(R.id.idGrade);
        division=findViewById(R.id.idDivision);
        subDivision=findViewById(R.id.idSubDivision);
        phylum=findViewById(R.id.idPhylum);
        group=findViewById(R.id.idGroup);
        subPhylum=findViewById(R.id.idSubPhylum);
        superClass=findViewById(R.id.idSuperClass);
        class1=findViewById(R.id.idClass);
        subClass=findViewById(R.id.idSubClass);
        infraClass=findViewById(R.id.idInfraClass);
        superOrder=findViewById(R.id.idSuperOrder);
        order=findViewById(R.id.idOrder);
        subOrder=findViewById(R.id.idSubOrder);
        infraOrder=findViewById(R.id.idInfraOrder);
        family=findViewById(R.id.idFamily);
        genus=findViewById(R.id.idGenus);
        description=findViewById(R.id.idDescription);
        ref=findViewById(R.id.idReferences);

        audio=findViewById(R.id.idAudio);
        extLinks=findViewById(R.id.idExternalLinks);
        view3D=findViewById(R.id.idView3DModel);

        getDetails();
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
//            try{
            selectedSpecimen= extras.getString("SciName");
//            }finally {
//                ProjectName = extras.getString("Common Name");
//                ScientificName= extras.getString("Sci Name");
//            }
        }
    }

    private void getDetails() {
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=zUjv73MQztNx4Tn3DruKNJ34W6cbGG65mJtueACmB0Kq_iu7NdHOb9AZMjfZzmN_OVEdCNs4ROXGfvZc1VIRbV11w2t6yrEYm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnNFki3g1hL3IUOyN12gWNmVBz2p2TKXc9Xuv3WMU4Wnz1LsR9TGQAsYlZRA1hBWNPk8_MUtqX4Zwp4r-nmUQb9Tx90esvHgeXA&lib=MbgrK457YjWEMRj1RpcABUG3CjCSjDOnU";

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    try {
                        Response response = client.newCall(request).execute();
                        Log.d(TAG, "getDetails: "+response.body().string());
                        updateViews(response);
                        if (loading.isShowing()) {
                            loading.dismiss();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        thread.start();

    }

    private void updateViews(Response response) {
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray= jsonObject.getJSONArray("details");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentSpecimen = jsonArray.getJSONObject(i);
                if (currentSpecimen.toString().equals(selectedSpecimen)){
                    commonName.setText(currentSpecimen.getString("Common Name"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}