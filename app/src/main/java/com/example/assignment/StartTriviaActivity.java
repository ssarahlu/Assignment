package com.example.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Data.MusicTrivia;
import com.example.assignment.Data.MusicTriviaList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.assignment.SelectLearningActivity.EXTRA_MESSAGE;

// Used the following repository for reference: https://github.com/EklavyaM/Trivia
public class StartTriviaActivity extends AppCompatActivity implements View.OnClickListener {

    private MusicTriviaResult g;
    private Button musicTrivia;
    private SharedPreferences preferences;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trivia);

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        g = MusicTriviaResult.getInstance(getApplicationContext());
        g.reset();

        getView();
        setTitle("Trivia Quiz");

    }

    private void getView() {
        musicTrivia = findViewById(R.id.musicTrivia);
        musicTrivia.setTag("music");
        musicTrivia.setOnClickListener(this);
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        String text = v.getTag().toString();
        g.chosenCategory = "12";
        getCurrentList(v);
    }


    private void getCurrentList(final View view) {
        final TextView textView = (TextView) view;

        Intent intent = getIntent();
        difficulty = intent.getStringExtra(EXTRA_MESSAGE).toLowerCase();

        g.getApiService().getQuestion("5", g.chosenCategory, difficulty, "multiple").enqueue(new Callback<MusicTriviaList>() {
            @Override
            public void onResponse(Call<MusicTriviaList> call, Response<MusicTriviaList> response) {
                if (response.isSuccessful()) {
                    MusicTriviaList list = response.body();
                    List<MusicTrivia> ls = list.getResults();

                    if (ls.isEmpty()) {
                        getCurrentList(view);
                    } else {
                        g.currentList.clear();
                        g.currentList.addAll(ls);
                        g.setDifficulty(difficulty);
                        g.loadNextList();
                        Intent intent = new Intent(StartTriviaActivity.this, TriviaQuestionActivity.class);
                        intent.putExtra("difficulty", difficulty);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(StartTriviaActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MusicTriviaList> call, Throwable t) {
                Toast.makeText(StartTriviaActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                textView.setTextColor(Color.WHITE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
