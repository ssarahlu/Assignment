package com.example.assignment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.room.Room;

import com.example.assignment.Data.MusicTrivia;
import com.example.assignment.Data.MusicTriviaList;
import com.example.assignment.Entities.QuizResult;
import com.example.assignment.Retrofit.MusicTriviaService;
import com.example.assignment.Retrofit.MusicTriviaUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
    MyDatabase myDb;
    private String email;
    private static final String TAG = "Global";
    private String score_string, correct_string, total_string;
    private int myDbresult;
    private QuizResult qr;
    TextView scoret, correct_number, total_questions, highscore;
    Button exit_button, exit;
    ImageView image;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    private Global(Context context) {
        mContext = context;
        mAPIService = MusicTriviaUtils.getAPIService();
        currentList = new ArrayList<>();
        nextList = new ArrayList<>();
    }

    public static synchronized Global getInstance(Context context) {
        if (instance == null)
            instance = new Global(context);
        return instance;
    }

    public MusicTriviaService getApiService() {
        return mAPIService;
    }

    public void correctAnswer(int marks) {
        score += marks;
    }

    public int getScore() {
        return score;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void reset() {
        score = 0;
        questionsEncountered = 1;
        questionsCorrectlyAnswered = 0;
        isGameOver = false;
    }

    public void loadNextList() {

        getApiService().getQuestion("5", chosenCategory, difficulty, "multiple").enqueue(new Callback<MusicTriviaList>() {
            @Override
            public void onResponse(Call<MusicTriviaList> call, Response<MusicTriviaList> response) {
                System.out.println(difficulty);
                MusicTriviaList list = response.body();
                if (!list.getResults().isEmpty()) {
                    nextList.clear();
                    nextList.addAll(list.getResults());
                } else {
                    loadNextList();
                }
            }

            @Override
            public void onFailure(Call<MusicTriviaList> call, Throwable t) {
                nextList.clear();
            }
        });

    }

    public void gameOver(final Context context) {
        isGameOver = true;

        progressBar = ((TriviaQuestionActivity) context).progressBar;
        progressBar.setIndeterminate(true);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            email = acct.getEmail();
        }

        showDialogGameOver(context, false, "Game Over");

    }

    private String getCategoryName() {
        String category = "Music";
        return category;
    }

    private void showDialogGameOver(final Context context, final boolean triviaLoadedSuccessfully, final String errorIfAny) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog);
        dialog.setContentView(R.layout.score_window);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("Your Score");

        scoret = dialog.findViewById(R.id.exit_score);
        correct_number = dialog.findViewById(R.id.correct_number);
        total_questions = dialog.findViewById(R.id.total_questions);
        highscore = dialog.findViewById(R.id.highscore);
        exit = dialog.findViewById(R.id.exit);
        image = dialog.findViewById(R.id.image);

        correct_string = Integer.toString(questionsCorrectlyAnswered);
        total_string = Integer.toString(questionsEncountered);
        score_string = Integer.toString(getScore());

        new GetStoredScore().execute();
        new InsertScoresTask().execute();
        new UpdateScoresTask().execute();


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, SelectQuizDifficulty.class);
                ((TriviaQuestionActivity) context).startActivity(intent);

            }

        });

        dialog.show();
    }


    private void updateUi() {
        if (myDbresult > Integer.valueOf(score_string)) {
            correct_number.setText(correct_string);
            total_questions.setText(total_string);
            scoret.setText(score_string);
            image.setImageResource(R.drawable.no_star);
            highscore.setText("You did not beat your previous high score of " + myDbresult);

        } else if (myDbresult == Integer.valueOf(score_string)) {
            correct_number.setText(correct_string);
            total_questions.setText(total_string);
            scoret.setText(score_string);
            image.setImageResource(R.drawable.neutral);
            highscore.setText("You got the same score as your current high score " + Integer.valueOf(score_string));

        } else if (myDbresult == 0) {
            correct_number.setText(correct_string);
            total_questions.setText(total_string);
            scoret.setText(score_string);
            image.setImageResource(R.drawable.happy);
            highscore.setText("Your recorded high score is " + Integer.valueOf(score_string));
        } else {
            correct_number.setText(correct_string);
            total_questions.setText(total_string);
            scoret.setText(score_string);
            image.setImageResource(R.drawable.happy);
            highscore.setText("Congratulations! You beat your previous high score of " + myDbresult + ". Your new high score is " + Integer.valueOf(score_string));
        }


    }

    private class InsertScoresTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(mContext, MyDatabase.class, "my-db.db")
                    .build();

            myDb.quizResultDao().insertSingleResult(email, difficulty, Integer.valueOf(score_string), "trivia");

            Log.d(TAG, "doInBackground:  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private class UpdateScoresTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(mContext, MyDatabase.class, "my-db.db")
                    .build();

            if (Integer.valueOf(score_string) > myDbresult) {
                qr = new QuizResult(email, difficulty, Integer.valueOf(score_string), "trivia");
                myDb.quizResultDao().updateQuizResult(qr);
                Log.d(TAG, "doInBackground: New result updated! ");
            }
            Log.d(TAG, "doInBackground:  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            updateUi();
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private class GetStoredScore extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(mContext, MyDatabase.class, "my-db.db")
                    .build();

            myDbresult = myDb.quizResultDao().getResult(email, difficulty, "trivia");
            Log.d(TAG, "doInBackground: get my saved result " + myDbresult);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }


}