package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUP extends AppCompatActivity {
    private Button signInBtn;
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signInBtn=findViewById(R.id.idSignUpBTN);
        email=findViewById(R.id.idEmailSignUpET);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUP.this, Scanner.class);
                intent.putExtra("name",email.getText());
                startActivity(intent);
            }
        });
    }
}