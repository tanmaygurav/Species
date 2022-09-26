package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Botany_Specimen_Details extends AppCompatActivity {
    String urlBT="https://script.googleusercontent.com/macros/echo?user_content_key=0UTF_YlcTosLx4LvfI8Y-jqHq5uNMxLNUlQo50ys0a-mOydPhE-vOmVsK862DsTgZMbwXQbRHYRnRoVcIFVQi-SCXx18CLOfm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnPAg68g5_LIVhOFliTZOHp_Pe53hTzepE9HKrIBVyWuXk3JRqI14l7eeF2SS8MUv_KlAO8UtLHErd1Nu36CMHRTFHgsModOtfw&lib=Mjs4oHSyTOvACHWjwQDMgGW3CjCSjDOnU";
    private static final String TAG = "Botany_Specimen_Details";
    TextView commonName, sciName, kingdom, division, subDivision, class1, subClass,
            order, subOrder, series, cohort, family, genus, description, ref1, ref2, ref3;
    String commonNameTxt, sciNameTxt, kingdomTxt, divisionTxt, subDivisionTxt, class1Txt, subClassTxt,
            orderTxt, subOrderTxt, seriesTxt, cohortTxt, familyTxt, genusTxt, descriptionTxt, ref1Txt,
            ref2Txt, ref3Txt,morphImageTxt,reproImageTxt;
    Button morphImage, reproImage;
    RelativeLayout moreKingdomViews, moreGenusViews;
    TextView moreKingdom,moreGenus;
    String selectedSpecimen;
    ProgressDialog loading;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botany_specimen_details);

        commonName=findViewById(R.id.idSpecimenCommonName);
        sciName=findViewById(R.id.idSpecimenSciName);
        kingdom=findViewById(R.id.idKingdom);
        division=findViewById(R.id.idDivision);
        subDivision=findViewById(R.id.idSubDivision);
        class1=findViewById(R.id.idClass);
        subClass=findViewById(R.id.idSubClass);
        order=findViewById(R.id.idOrder);
        subOrder=findViewById(R.id.idSubOrder);
        series=findViewById(R.id.idSeries);
        cohort=findViewById(R.id.idCohort);
        family=findViewById(R.id.idFamily);
        genus=findViewById(R.id.idGenus);
        description=findViewById(R.id.idDescription);
        ref1=findViewById(R.id.idReferences1);
        ref2=findViewById(R.id.idReferences2);
        ref3=findViewById(R.id.idReferences3);

        morphImage=findViewById(R.id.idMorphologyImage);
        reproImage=findViewById(R.id.idReproductiveImage);

        moreKingdom=findViewById(R.id.idMoreKingdom);
        moreGenus=findViewById(R.id.idMoreGenus);
        moreGenusViews=findViewById(R.id.idMoreGenusViews);
        moreKingdomViews=findViewById(R.id.idMoreKingdomViews);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            selectedSpecimen= extras.getString("SciName");
            Log.d(TAG, "onCreate: SciName"+selectedSpecimen);
        }
        getDetails();
//        show first view till kingdom only hide rest
        moreKingdomViews.setVisibility(View.GONE);
        moreGenusViews.setVisibility(View.GONE);
//        show views from kingdom to genus when user clicks more
        moreKingdom.setOnClickListener(view -> {
            moreKingdomViews.setVisibility(View.VISIBLE);
            moreKingdom.setVisibility(View.GONE);
        });

//        show views from genus to end when user clicks more
        moreGenus.setOnClickListener(view -> {
            moreGenusViews.setVisibility(View.VISIBLE);
            moreGenus.setVisibility(View.GONE);
        });

        morphImage.setOnClickListener(view -> {
            if (!morphImageTxt.equals("Not Available")) {
                Intent intent1 = new Intent(getApplicationContext(), FullScrnImage.class);
                intent1.putExtra("link", morphImageTxt);
                startActivity(intent1);
            }else {
                Toast.makeText(getApplicationContext(),"Image not Available",Toast.LENGTH_SHORT).show();
            }

        });

        reproImage.setOnClickListener(view -> {
            if (!reproImageTxt.equals("Not Available")) {
                Intent intent1 = new Intent(getApplicationContext(), FullScrnImage.class);
                intent1.putExtra("link", reproImageTxt);
                startActivity(intent1);
            }else {
                Toast.makeText(getApplicationContext(),"Image not Available",Toast.LENGTH_SHORT).show();
            }

        });

//        ref onclick
        ref1.setOnClickListener(view -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ref1.getText().toString()));
                startActivity(browserIntent);
            }catch (Exception e){
                Log.d(TAG, "onCreate: Reference link "+e);
                Toast.makeText(getApplicationContext(),"URl Not Found",Toast.LENGTH_SHORT).show();
            }

        });

        ref2.setOnClickListener(view -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ref2.getText().toString()));
                startActivity(browserIntent);
            }catch (Exception e){
                Log.d(TAG, "onCreate: Reference link "+e);
                Toast.makeText(getApplicationContext(),"URl Not Found",Toast.LENGTH_SHORT).show();
            }

        });

        ref3.setOnClickListener(view -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ref3.getText().toString()));
                startActivity(browserIntent);
            }catch (Exception e){
                Log.d(TAG, "onCreate: Reference link "+e);
                Toast.makeText(getApplicationContext(),"URl Not Found",Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getDetails() {
        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);
        Thread thread= new Thread(() -> {
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
//                            Log.d(TAG, "run: object.toString"+object.toString());
                        sciNameTxt= object.getString("Scientific Name");
                        Log.d(TAG, "run: sciNameTxt"+sciNameTxt);
                        if (sciNameTxt.equals(selectedSpecimen)){
                            Log.d(TAG, "getDetails: Specimen Found"+sciNameTxt);
                            Botany_Specimen_Details.this.runOnUiThread(() -> getValues(object));
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

            divisionTxt=object.getString("Division");
            if (divisionTxt.isEmpty()) divisionTxt="Not Available";

            subDivisionTxt=object.getString("Sub-Division");
            if (subDivisionTxt.isEmpty()) subDivisionTxt="Not Available";

            class1Txt=object.getString("Class");
            if (class1Txt.isEmpty()) class1Txt="Not Available";

            subClassTxt=object.getString("Sub-Class");
            if (subClassTxt.isEmpty()) subClassTxt="Not Available";

            orderTxt=object.getString("Order");
            if (orderTxt.isEmpty()) orderTxt="Not Available";

            subOrderTxt=object.getString("Sub-Order");
            if (subOrderTxt.isEmpty()) subOrderTxt="Not Available";

            seriesTxt=object.getString("Series");
            if (seriesTxt.isEmpty()) seriesTxt="Not Available";

            cohortTxt=object.getString("Cohort");
            if (cohortTxt.isEmpty()) cohortTxt="Not Available";

            familyTxt=object.getString("Family");
            if (familyTxt.isEmpty() || familyTxt.equals("Other")) familyTxt= object.getString("Family2");
            if (familyTxt.isEmpty()) familyTxt="Not Available";

            genusTxt=object.getString("Genus");
            if (genusTxt.isEmpty() || genusTxt.equals("Other")) genusTxt= object.getString("Genus2");
            if (genusTxt.isEmpty()) genusTxt="Not Available";

            descriptionTxt=object.getString("Description");
            if (descriptionTxt.isEmpty()) descriptionTxt="Not Available";

            ref1Txt=object.getString("References1");
            if (ref1Txt.isEmpty()) ref1Txt="Not Available";

            ref2Txt=object.getString("References2");
            if (ref2Txt.isEmpty()) ref2Txt="Not Available";

            ref3Txt=object.getString("References3");
            if (ref3Txt.isEmpty()) ref3Txt="Not Available";

            morphImageTxt=object.getString("Morphology");
            if (morphImageTxt.isEmpty()) morphImageTxt="Not Available";

            reproImageTxt=object.getString("Reproductive");
            if (morphImageTxt.isEmpty()) morphImageTxt="Not Available";
            updateViews();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateViews() {
        try {
            commonName.setText(commonNameTxt);
            sciName.setText(sciNameTxt);
            kingdom.setText(kingdomTxt);
            division.setText(divisionTxt);
            subDivision.setText(subDivisionTxt);
            class1.setText(class1Txt);
            subClass.setText(subClassTxt);
            order.setText(orderTxt);
            subOrder.setText(subOrderTxt);
            series.setText(seriesTxt);
            cohort.setText(cohortTxt);
            family.setText(familyTxt);
            genus.setText(genusTxt);
            description.setText(descriptionTxt);
            ref1.setText(ref1Txt);
            ref2.setText(ref2Txt);
            ref3.setText(ref3Txt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}