package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {
    private static final String TAG = "Home";
    private final String username="DEMO";
    private TextView userName;
    private Button qr, allSpecimensZ,allSpecimensB;

//    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
//    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName=findViewById(R.id.idUserName);
        qr=findViewById(R.id.idScanQR);
        allSpecimensZ=findViewById(R.id.idAllSpecimensZ);
        allSpecimensB=findViewById(R.id.idAllSpecimensB);

        db = FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//        firebaseUser = mAuth.getCurrentUser();

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {
            String j = extras.getString("name");
            userName.setText(j);
//            userName.setText(firebaseUser.getDisplayName());
        }else {
            userName.setText(username);
        }

        allSpecimensZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref("Zoology Specimens");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("CupboardNo","Zoology Specimens");
                startActivity(intent);
            }
        });

        allSpecimensB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref("Biology Specimens");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("CupboardNo","Biology Specimens");
                startActivity(intent);
            }
        });

        qr.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),Scanner.class));
        });

    }

    private void savePref(String dep) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Dep",dep);


        editor.apply();
    }
}