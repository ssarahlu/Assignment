package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Entities.Account;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * REFERENCE for Google Login API
 * https://www.youtube.com/watch?v=t-yZUqthDMM
 */

public class ProfileActivity extends AppCompatActivity {
    private TextView name, email, id, stars, helpCentre, help;
    private ImageView imageView;
    private Button signOut;
    private ImageButton helpButton, feedback;
    GoogleSignInClient mGoogleSignInClient;
    MyDatabase myDb;
    private String personName, personEmail, personFName, personLname;
    private static final String TAG = "ProfileActivity";
    BottomNavigationView bottomNavigation;
    private int mStars;
    GoogleSignInAccount acct;
    private Dialog feedbackDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My profile");
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
        stars = findViewById(R.id.stars);
        helpCentre = findViewById(R.id.help2);
        helpButton = findViewById(R.id.helpButton);
        feedback = findViewById(R.id.feedback);
        help = findViewById(R.id.help);
        feedbackDialog = new Dialog(this);

        new GetTotalStars().execute();

        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setItemTextColor(null);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.learning:
                        intent = new Intent(getApplicationContext(), SelectDifficulty.class);
                        startActivity(intent);
                        return true;
                    case R.id.quiz:
                        intent = new Intent(getApplicationContext(), QuizActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.reward:
                        intent = new Intent(getApplicationContext(), RewardActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.leaderboard:
                        intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

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

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpCentreActivity.class);
                startActivity(intent);

            }
        });

        helpCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpCentreActivity.class);
                startActivity(intent);

            }
        });

        // Feedback is just a prototype, dialog not actually stored anywhere
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        // Feedback is just a prototype, dialog not actually stored anywhere
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            personFName = acct.getGivenName();
            personLname = acct.getFamilyName();
            String personId = acct.getId();
            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            id.setVisibility(View.GONE);
            Glide.with(this).load(String.valueOf(acct.getPhotoUrl())).into(imageView);
            new AddUser().execute();
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


    private class GetTotalStars extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            mStars = myDb.topicResultDao().getTotalStars(personEmail);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            updateUi();
            super.onPostExecute(v);

        }

    }

    private class AddUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            myDb.accountDao().insert(new Account(personEmail, acct.getGivenName(), acct.getFamilyName()));
            Log.d(TAG, "onCreate: " + myDb.accountDao().getAcc(personEmail).getFName());
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

        }

    }

    private void updateUi() {
        if (mStars >= 0) {
            stars.setText(String.valueOf(mStars));
        } else {
            stars.setText("0");
        }

    }

    // Reference: https://www.youtube.com/watch?v=e3WfylNHHC4
    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
        dialog.setTitle("Your feedback is important to us");
        final EditText feedbackInput = new EditText(ProfileActivity.this);
        //feedbackInput.setSingleLine(false);
        feedbackInput.setLines(4);
        dialog.setView(feedbackInput);
        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ProfileActivity.this, "Feedback submitted!", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }

}
