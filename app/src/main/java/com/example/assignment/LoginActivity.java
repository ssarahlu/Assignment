package com.example.assignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

//this is the login activity that lets users login with their google account
//we used google login as it is more secure than storing passwords and emails in plain text in our database
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN_ACTIVITY";
    SignInButton signIn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    ProgressDialog progressDialog;

    /**
     * REFERENCE for Google Login API
     * https://www.youtube.com/watch?v=t-yZUqthDMM
     */

    /**
     * SAMPLE GOOGLE ACCOUNT TO USE FOR TESTING
     * EMAIL: keynotes3634@gmail.com
     * PASSWORD: keynotes
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.sign_in_button);
        getSupportActionBar().hide();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken("748117709797-gpul3b7tr723u9rd54ol7s5bl1e5eq59.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            //displays spinners for loading account data
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Loading",
                    "Logging in. Please wait.", true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            Log.w(TAG, "Error signIn Result Failed Code = " + e.getStatusCode());
        }
    }

    //if the user is already previously signed in, it will skip past the login option and take them straight to their profile
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    //will go straight to user profile if the user has already logged in
    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            //will display spinner for loading account information
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Loading",
                    "Logging " + account.getEmail() + " in. Please wait.", true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show(); // Display Progress Dialog
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            startActivity(intent);

        }
    }
}


