package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {
    private final String username="TANMAY";
    private TextView userName;
    private Button qrCupboard, qrSpecimen, allSpecimens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName=findViewById(R.id.idUserName);
        qrCupboard=findViewById(R.id.idScanCupboard);
        qrSpecimen=findViewById(R.id.idScanSpecimen);
        allSpecimens=findViewById(R.id.idAllSpecimens);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {
            String j = extras.getString("name");
            userName.setText(j);
        }else {
            userName.setText(username);
        }

        allSpecimens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("Cupboard","All Specimens");
                startActivity(intent);
            }
        });

    }
}