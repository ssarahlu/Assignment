package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN_ACTIVITY";
    private Button button, logout, enter;
    private TextView email, name;
    SignInButton signIn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;


    /**
     * REFERENCE for Google Login API
     * https://www.youtube.com/watch?v=t-yZUqthDMM
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.redeem);
        signIn = findViewById(R.id.sign_in_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectDifficulty.class);
                startActivity(intent);
            }
        });

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

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Log.w(TAG, "Error signIn Result:failed code=" + e.getStatusCode());
        }
    }


    /**
     * TODO for checking for existing signed in user
     */
    //use this to check for existing signed in user
//    @Override
//    protected void onStart(){
//        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);
//    }

//
//    private void updateUI(boolean isLogin)
//    {
//        if(isLogin)
//        {
//            name.setVisibility(View.VISIBLE);
//            email.setVisibility(View.VISIBLE);
//            logout.setVisibility(View.VISIBLE);
//            enter.setVisibility(View.VISIBLE);
//            signIn.setVisibility(View.GONE);
//        }
//        else
//        {
//            name.setVisibility(View.GONE);
//            email.setVisibility(View.GONE);
//            logout.setVisibility(View.GONE);
//            enter.setVisibility(View.GONE);
//            signIn.setVisibility(View.VISIBLE);
//        }
//    }
}

