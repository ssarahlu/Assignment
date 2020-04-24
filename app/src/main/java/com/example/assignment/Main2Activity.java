package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * REFERENCE for Google Login API
 * https://www.youtube.com/watch?v=t-yZUqthDMM
 */

public class Main2Activity extends AppCompatActivity {
    private TextView name, email, id;
    private ImageView imageView;
    private Button signOut, button;
    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken("748117709797-gpul3b7tr723u9rd54ol7s5bl1e5eq59.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        name = findViewById(R.id.name);
        imageView = findViewById(R.id.imageView);
        id = findViewById(R.id.id);
        email = findViewById(R.id.email);
        signOut = findViewById(R.id.signOut);
        button = findViewById(R.id.button);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signOut:
                        signOut();
                        break;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectDifficulty.class);
                startActivity(intent);

            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Intent intent = getIntent();
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();

            name.setText(personName);
            System.out.println(acct.getPhotoUrl());

            email.setText(personEmail);
            id.setText(personId);

            Glide.with(this).load(String.valueOf(acct.getPhotoUrl())).into(imageView);
        }


    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Signed out successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }


}
