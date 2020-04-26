package com.example.assignment.Retrofit;

public class MusicTriviaUtils {

    public static final String BASE_URL = "https://opentdb.com/";

    public static MusicTriviaService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(MusicTriviaService.class);
    }

}