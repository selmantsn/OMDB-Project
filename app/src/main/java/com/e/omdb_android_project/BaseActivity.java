package com.e.omdb_android_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class BaseActivity extends AppCompatActivity {

    public KProgressHUD progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        progressDialog = KProgressHUD.create(this)
                .setLabel("Yükleniyor..")
                .setDimAmount(0.35f)
                .setCancellable(false);


    }
}
