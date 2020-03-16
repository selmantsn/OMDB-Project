package com.e.omdb_android_project.api;

import com.e.omdb_android_project.model.reponse.FilmItem;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitServices {

    @GET("/")
    Call<FilmItem> getFilmList(
            @QueryMap Map<String,String> request
    );

}
