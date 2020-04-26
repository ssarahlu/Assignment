package com.example.assignment.Retrofit;

import com.example.assignment.Data.MusicTriviaList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicTriviaService {

    @GET("/api.php")
    Call<MusicTriviaList> getQuestion(@Query("amount") String amount, @Query("category") String category, @Query("difficulty") String difficulty, @Query("type") String type);

}
