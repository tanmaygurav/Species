package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUP extends AppCompatActivity {
    private Button signInBtn;
    private EditText email;
    private String name= "Username";
    private TextView getSignINPage;
    private ImageView adminPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signInBtn=findViewById(R.id.idSignUpBTN);
        email=findViewById(R.id.idEmailSignUpET);
        getSignINPage=findViewById(R.id.idSignUpPage);
        adminPage=findViewById(R.id.idLogo);


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUP.this, Scanner.class);
                if (email.getText().toString()==null){
                   name = "Username";
                }else{
                    name=email.getText().toString();
                }
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        getSignINPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUP.this,SignIN.class);
                startActivity(intent);
            }
        });

        adminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUP.this,AdminPanel.class);
                startActivity(intent);
            }
        });
    }
}