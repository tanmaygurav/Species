package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIN extends AppCompatActivity {
    private Button signInBtn;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInBtn=findViewById(R.id.idSignInBTN);
        email=findViewById(R.id.idEmailSignInET);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIN.this, Scanner.class);
                intent.putExtra("name",email.getText());
                startActivity(intent);
            }
        });
    }
}