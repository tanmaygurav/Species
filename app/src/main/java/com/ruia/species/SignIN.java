package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignIN extends AppCompatActivity {
    private Button signInBtn;
    private EditText email;
    private String name= "Username";
    private TextView getSignupPage;
    private ImageView adminPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInBtn=findViewById(R.id.idSignInBTN);
        email=findViewById(R.id.idEmailSignInET);
        getSignupPage=findViewById(R.id.idSignInPage);
        adminPage=findViewById(R.id.idLogo);


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIN.this, Scanner.class);
                if (email.getText().toString()==null){
                    name = "Username";
                }else{
                    name=email.getText().toString();
                }
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        getSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIN.this,SignUP.class);
                startActivity(intent);
            }
        });

        adminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIN.this,AdminPanel.class);
                startActivity(intent);
            }
        });

        adminPage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(SignIN.this,MainActivity.class);
                intent.putExtra("Cupboard","Cupboard");
                startActivity(intent);
                return true;
            }
        });
    }
}