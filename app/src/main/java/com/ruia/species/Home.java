package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private static final String TAG = "Home";
    private final String username="DEMO";
    private TextView userName;
//    private Button qrB,qrZ, allSpecimensZ,allSpecimensB;
    private CardView qrB,qrZ, allSpecimensZ,allSpecimensB;

//    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
//    private FirebaseUser firebaseUser;
private RecyclerView recyclerView;
    private ArrayList<HomeModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName=findViewById(R.id.idUserName);
//        qrB=findViewById(R.id.idScanQRB);
//        qrZ=findViewById(R.id.idScanQRZ);
//        allSpecimensZ=findViewById(R.id.idAllSpecimensZ);
//        allSpecimensB=findViewById(R.id.idAllSpecimensB);

        qrB=findViewById(R.id.idScanQRBCard);
        qrZ=findViewById(R.id.idScanQRZCard);
        allSpecimensZ=findViewById(R.id.idAllSpecimensZCard);
        allSpecimensB=findViewById(R.id.idAllSpecimensBCard);

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
                savePref("Botany Specimens");
                Intent intent = new Intent(getApplicationContext(),BotanyMainActivity.class);
                intent.putExtra("CupboardNo","Botany Specimens");
                startActivity(intent);
            }
        });

        qrB.setOnClickListener(view -> {
            savePref("Botany Specimens");
            Intent intent1=new Intent(getApplicationContext(),Scanner.class);
            intent1.putExtra("dep","Botany Specimens");
            startActivity(intent1);
        });

        qrZ.setOnClickListener(view -> {
            savePref("Zoology Specimens");
            Intent intent2=new Intent(getApplicationContext(),Scanner.class);
            intent2.putExtra("dep","Zoology Specimens");
            startActivity(intent2);
        });

    }

    private void savePref(String dep) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Dep",dep);

        editor.apply();
    }
}