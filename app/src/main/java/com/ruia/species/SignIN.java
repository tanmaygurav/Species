package com.ruia.species;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIN extends AppCompatActivity {
    private final String TAG="SignIN";
    private Button signInBtn;
    private EditText email,password;
    private String name= "Username";
    private TextView getSignupPage;
    private ImageView adminPage;
//    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirebaseApp.initializeApp(getApplicationContext());

        signInBtn=findViewById(R.id.idSignInBTN);
        email=findViewById(R.id.idEmailSignInET);
        getSignupPage=findViewById(R.id.idSignInPage);
        adminPage=findViewById(R.id.idLogo);

        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


//        signInBtn.setOnClickListener(view -> signIn(email.getText().toString(),password.getText().toString()));
        signInBtn.setOnClickListener(view -> startActivity(new Intent(SignIN.this,Home.class)));

        getSignupPage.setOnClickListener(view -> startActivity(new Intent(SignIN.this,SignUP.class)));


    }

    /*private void signIn(String email,String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

}