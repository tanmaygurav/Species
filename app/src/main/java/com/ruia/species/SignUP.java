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
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUP extends AppCompatActivity {
    private final String TAG="SignUP";
    private Button signInBtn;
    private EditText email,password,username;
    private String name= "Username";
    private TextView getSignINPage;
    private ImageView adminPage;
//    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signInBtn=findViewById(R.id.idSignUpBTN);
        email=findViewById(R.id.idEmailSignUpET);
        password=findViewById(R.id.idPasswordSignUpET);
        username=findViewById(R.id.idNameSignUpET);
        getSignINPage=findViewById(R.id.idSignUpPage);
        adminPage=findViewById(R.id.idLogo);

        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


//        signInBtn.setOnClickListener(view -> createAccount(email.getText().toString(),password.getText().toString()));
        signInBtn.setOnClickListener(view -> startActivity(new Intent(SignUP.this,Home.class)));

        getSignINPage.setOnClickListener(view -> startActivity(new Intent(SignUP.this,SignIN.class)));

    }

  /*  private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Authentication Successful, Now Login.",
                                    Toast.LENGTH_SHORT).show();
//                            save to db
                            savetodb(user.getUid(),username.getText().toString(),email);
                            updateUI(user);
                            startActivity(new Intent(getApplicationContext(), SignIN.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }*/

    /*private void updateUI(FirebaseUser user) {
        user.updateEmail(email.getText().toString());
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username.getText().toString()).build();
        user.updateProfile(profileUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "User profile updated.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"User Display Name Could not be set :",e);
                    }
                });
    }*/

    private void savetodb(String uid, String username, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("username",username);
        user.put("uid",uid);
        user.put("email", email);
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document"+ e);
                    }
                });
    }
}