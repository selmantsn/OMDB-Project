package com.e.omdb_android_project.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.omdb_android_project.model.reponse.FilmItem;

public class MainVievModel extends ViewModel {

    public MutableLiveData<FilmItem> filmLiveData = new MutableLiveData<>();
    private FilmListRepository filmListRepository = new FilmListRepository();



    public void callApi(String search) {
        filmListRepository.getFilms(filmLiveData, search);
    }

}
