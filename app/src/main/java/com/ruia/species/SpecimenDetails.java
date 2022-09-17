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
    String url = "https://script.googleusercontent.com/macros/echo?user_content_key=0J5tuct4yFB2g6fEWdj_JEXbJ5hHLoQZflDRM1U8g_iii8fS6Nd9Sb810E0MK4pkEtXONm4T19c-aMVlYFHY62ILEPD0aHIAm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnISMKmT4sc-oYr2XF2gzS2kVzK3LJnQf1PUbGcvJ9F1imRpjEuoH3KBIxBXgLlSBCu9v9620l5oJVMnCcIoHKO2DwEENJfcyXNz9Jw9Md8uu&lib=MafhO8DCDQWgRY--MA9fA4s4nyNeH4Rra";
    private static final String TAG = "SpecimenDetails";
    TextView commonName, sciName, kingdom, subKingdom, infraKingdom, grade, division,
            subDivision, phylum, group, subPhylum, superClass, class1, subClass,
            infraClass, superOrder, order, subOrder, infraOrder, family, genus, description, ref;
    String commonNameTxt, sciNameTxt, kingdomTxt, subKingdomTxt, infraKingdomTxt, gradeTxt, divisionTxt,
            subDivisionTxt, phylumTxt, groupTxt, subPhylumTxt, superClassTxt, class1Txt, subClassTxt,
            infraClassTxt, superOrderTxt, orderTxt, subOrderTxt, infraOrderTxt, familyTxt, genusTxt, descriptionTxt, refTxt;
    Button audio, extLinks, view3D;
    String selectedSpecimen;
    ProgressDialog loading;
    OkHttpClient client = new OkHttpClient();



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
            selectedSpecimen= extras.getString("SciName");
            Log.d(TAG, "onCreate: SciName"+selectedSpecimen);
        }
        
        view3D.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(),AR.class);
            intent1.putExtra("SciName",selectedSpecimen);
            startActivity(intent1);
        });
    }



    private void getDetails() {
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
            try {

                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
//                        Log.d(TAG, "run: jsonData"+jsonData);
                    JSONObject Jobject = new JSONObject(jsonData);
                    JSONArray Jarray = Jobject.getJSONArray("details");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
//                            Log.d(TAG, "run: object.toString"+object.toString());
                        sciNameTxt= object.getString("Scientific Name");
                        Log.d(TAG, "run: sciNameTxt"+sciNameTxt);
                        if (sciNameTxt.equals(selectedSpecimen)){


                            SpecimenDetails.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getValues(object);
                                }
                            });
                            break;
                        }
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

    }

    private void getValues(JSONObject object) {
        try {
            commonNameTxt=object.getString("Common Name");
            sciNameTxt=object.getString("Scientific Name");
            kingdomTxt=object.getString("Kingdom");

            subKingdomTxt=object.getString("Sub-Kingdom");
            if (subKingdomTxt.isEmpty() || subKingdomTxt.equals("Other")) subKingdomTxt= object.getString("Sub-Kingdom2");
            if (subKingdomTxt.isEmpty()) subKingdomTxt="Not Available";

            infraKingdomTxt=object.getString("Infra-Kingdom");
            if (infraKingdomTxt.isEmpty() || infraKingdomTxt.equals("Other")) infraKingdomTxt= object.getString("Infra-Kingdom2");
            if (infraKingdomTxt.isEmpty()) infraKingdomTxt="Not Available";

            gradeTxt=object.getString("Grade");
            if (gradeTxt.isEmpty() || gradeTxt.equals("Other")) gradeTxt= object.getString("Grade2");
            if (gradeTxt.isEmpty()) gradeTxt="Not Available";

            divisionTxt=object.getString("Division");
            if (divisionTxt.isEmpty() || divisionTxt.equals("Other")) divisionTxt= object.getString("Division2");
            if (divisionTxt.isEmpty()) divisionTxt="Not Available";

            subDivisionTxt=object.getString("Sub-Division");
            if (subDivisionTxt.isEmpty() || subDivisionTxt.equals("Other")) subDivisionTxt= object.getString("Sub-Division2");
            if (subDivisionTxt.isEmpty()) subDivisionTxt="Not Available";

            phylumTxt=object.getString("Phylum");
            if (phylumTxt.isEmpty() || phylumTxt.equals("Other")) phylumTxt= object.getString("Phylum2");
            if (phylumTxt.isEmpty()) phylumTxt="Not Available";

            groupTxt=object.getString("Group");
            if (groupTxt.isEmpty() || groupTxt.equals("Other")) groupTxt= object.getString("Group2");
            if (groupTxt.isEmpty()) groupTxt="Not Available";

            subPhylumTxt=object.getString("Sub-Phylum");
            if (subPhylumTxt.isEmpty() || subPhylumTxt.equals("Other")) subPhylumTxt= object.getString("Sub-Phylum2");
            if (subPhylumTxt.isEmpty()) subPhylumTxt="Not Available";

            superClassTxt=object.getString("Super-Class");
            if (superClassTxt.isEmpty() || superClassTxt.equals("Other")) superClassTxt= object.getString("Super-Class2");
            if (superClassTxt.isEmpty()) superClassTxt="Not Available";

            class1Txt=object.getString("Class");
            if (class1Txt.isEmpty() || class1Txt.equals("Other")) class1Txt= object.getString("Class2");
            if (class1Txt.isEmpty()) class1Txt="Not Available";

            subClassTxt=object.getString("Sub-Class");
            if (subClassTxt.isEmpty() || subClassTxt.equals("Other")) subClassTxt= object.getString("Sub-Class2");
            if (subClassTxt.isEmpty()) subClassTxt="Not Available";

            infraClassTxt=object.getString("Infra-Class");
            if (infraClassTxt.isEmpty() || infraClassTxt.equals("Other")) infraClassTxt= object.getString("Infra-Class2");
            if (infraClassTxt.isEmpty()) infraClassTxt="Not Available";

            superOrderTxt=object.getString("Super-Order");
            if (superOrderTxt.isEmpty() || superOrderTxt.equals("Other")) superOrderTxt= object.getString("Super-Order2");
            if (superOrderTxt.isEmpty()) superOrderTxt="Not Available";

            orderTxt=object.getString("Order");
            if (orderTxt.isEmpty() || orderTxt.equals("Other")) orderTxt= object.getString("Order2");
            if (orderTxt.isEmpty()) orderTxt="Not Available";

            subOrderTxt=object.getString("Sub-Order");
            if (subOrderTxt.isEmpty() || subOrderTxt.equals("Other")) subOrderTxt= object.getString("Sub-Order2");
            if (subOrderTxt.isEmpty()) subOrderTxt="Not Available";

            infraOrderTxt=object.getString("Infra-Order");
            if (infraOrderTxt.isEmpty() || infraOrderTxt.equals("Other")) infraOrderTxt= object.getString("Infra-Order2");
            if (infraOrderTxt.isEmpty()) infraOrderTxt="Not Available";

            familyTxt=object.getString("Family");
            if (familyTxt.isEmpty() || familyTxt.equals("Other")) familyTxt= object.getString("Family2");
            if (familyTxt.isEmpty()) familyTxt="Not Available";

            genusTxt=object.getString("Genus");
            if (genusTxt.isEmpty() || genusTxt.equals("Other")) genusTxt= object.getString("Genus2");
            if (genusTxt.isEmpty()) genusTxt="Not Available";

            descriptionTxt=object.getString("Description");
            if (descriptionTxt.isEmpty()) descriptionTxt="Not Available";

            refTxt=object.getString("References");
            if (refTxt.isEmpty()) refTxt="Not Available";
            updateViews();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void updateViews() {
        try {
            commonName.setText("Common Name: "+commonNameTxt);
            sciName.setText("Scientific Name: "+sciNameTxt);
            kingdom.setText("Kingdom: "+kingdomTxt);
            subKingdom.setText("Sub-Kingdom: "+subKingdomTxt);
            infraKingdom.setText("Infra-Kingdom: "+infraKingdomTxt);
            grade.setText("Grade: "+gradeTxt);
            division.setText("Division: "+divisionTxt);
            subDivision.setText("Sub-Division: "+subDivisionTxt);
            phylum.setText("Phylum: "+phylumTxt);
            group.setText("Group: "+groupTxt);
            subPhylum.setText("Sub-Phylum: "+subPhylumTxt);
            superClass.setText("Super-Class: "+superClassTxt);
            class1.setText("Class: "+class1Txt);
            subClass.setText("Sub-Class: "+subClassTxt);
            infraClass.setText("Infra-Class: "+infraClassTxt);
            superOrder.setText("Super-Order: "+superOrderTxt);
            order.setText("Order: "+orderTxt);
            subOrder.setText("Sub-Order: "+subOrderTxt);
            infraOrder.setText("Infra-Order: "+infraOrderTxt);
            family.setText("Family: "+familyTxt);
            genus.setText("Genus: "+genusTxt);
            description.setText("Description: "+descriptionTxt);
            ref.setText("References: "+refTxt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}