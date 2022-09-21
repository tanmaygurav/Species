package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullScrnImage extends AppCompatActivity {
    private static final String TAG = "FullScrnImage";
    String link;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_scrn_image);

        image=findViewById(R.id.idFullScrnImage);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            link= extras.getString("link");
            Log.d(TAG, "onCreate: link "+link);
        }

        try {
            Log.d(TAG, "onBindViewHolder: substring"+link.substring(0,24));
            String[] p=link.split("/");
            //Create the new image link
            String imageLink="https://drive.google.com/uc?export=download&id="+p[5];
            Picasso.get().load(imageLink).into(image);
            Log.d("Adapter", "onBindViewHolder: Image Set");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}