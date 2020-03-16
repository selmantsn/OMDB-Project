package com.e.omdb_android_project.ui;

import androidx.lifecycle.MutableLiveData;

import com.e.omdb_android_project.api.RetrofitClient;
import com.e.omdb_android_project.api.RetrofitServices;
import com.e.omdb_android_project.model.reponse.FilmItem;
import com.e.omdb_android_project.util.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmListRepository {

    public void getFilms(MutableLiveData<FilmItem> filmData, String searchText) {

        RetrofitServices retrofitInterface = RetrofitClient.getApiClient().create(RetrofitServices.class);

        Map<String, String> req = new HashMap<>();
        req.put("apikey", Constants.ApiKey);
        req.put("t", searchText);

        Call<FilmItem> call = retrofitInterface.getFilmList(req);

        call.enqueue(new Callback<FilmItem>() {
            @Override
            public void onResponse(Call<FilmItem> call, Response<FilmItem> response) {
                if (response.code() == 200) {

                    FilmItem filmItem = response.body();
                    filmData.setValue(filmItem);

                }
            }

            @Override
            public void onFailure(Call<FilmItem> call, Throwable t) {
                filmData.setValue(null);

            }
        });

    }

}
