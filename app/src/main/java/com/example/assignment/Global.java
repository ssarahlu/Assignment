package com.example.assignment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.assignment.Data.MusicTrivia;
import com.example.assignment.Data.MusicTriviaList;
import com.example.assignment.Retrofit.MusicTriviaService;
import com.example.assignment.Retrofit.MusicTriviaUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Used the following repository for reference: https://github.com/EklavyaM/Trivia

public class Global {
    private static Global instance;

    private static MusicTriviaService mAPIService;

    private Context mContext;

    public List<MusicTrivia> currentList;
    public List<MusicTrivia> nextList;

    public int questionsCorrectlyAnswered = 0;
    public int questionsEncountered = 1;

    public static final String EXTRA_MESSAGE = "difficulty";

    private String difficulty;

    private int score = 0;

    public String chosenCategory;

    private boolean isGameOver = false;

    private ProgressBar progressBar;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    private Global(Context context){
        mContext = context;
        mAPIService = MusicTriviaUtils.getAPIService();
        currentList = new ArrayList<>();
        nextList = new ArrayList<>();
    }

    public static synchronized Global getInstance(Context context){
        if(instance == null)
            instance = new Global(context);
        return instance;
    }

    public MusicTriviaService getApiService(){
        return mAPIService;
    }

    public void correctAnswer(int marks){
        score += marks;
    }

    public int getScore(){
        return score;
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public void reset(){
        score = 0;
        questionsEncountered = 1;
        questionsCorrectlyAnswered = 0;
        isGameOver = false;
    }

    public void loadNextList(){

        getApiService().getQuestion("5", chosenCategory, difficulty, "multiple").enqueue(new Callback<MusicTriviaList>() {
            @Override
            public void onResponse(Call<MusicTriviaList> call, Response<MusicTriviaList> response) {
                MusicTriviaList list = response.body();
                if(!list.getResults().isEmpty()) {
                    nextList.clear();
                    nextList.addAll(list.getResults());
                }
                else{
                    loadNextList();
                }
            }

            @Override
            public void onFailure(Call<MusicTriviaList> call, Throwable t) {
                nextList.clear();
            }
        });

    }

    public void gameOver(final Context context){
        isGameOver = true;

        progressBar = ((TriviaQuestionActivity) context).progressBar;
        progressBar.setIndeterminate(true);

        showDialogGameOver(context, false, "Game Over");

    }

    private String getCategoryName(){
        String category = "Music";
        return category;
    }

    private void showDialogGameOver(final Context context, final boolean triviaLoadedSuccessfully, final String errorIfAny){
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog);
        dialog.setContentView(R.layout.score_window);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("Your Score");

        TextView score, correct_number, total_questions;
        Button exit_button;

        score = dialog.findViewById(R.id.exit_score);
        exit_button = dialog.findViewById(R.id.exit_button);
        correct_number = dialog.findViewById(R.id.correct_number);
        total_questions = dialog.findViewById(R.id.total_questions);

        String correct_string = Integer.toString(questionsCorrectlyAnswered);
        String total_string = Integer.toString(questionsEncountered);
        String score_string = Integer.toString(getScore());

        correct_number.setText(correct_string);
        total_questions.setText(total_string);
        score.setText(score_string);

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ((TriviaQuestionActivity)context).onBackPressed();
            }

        });

        dialog.show();
    }


}