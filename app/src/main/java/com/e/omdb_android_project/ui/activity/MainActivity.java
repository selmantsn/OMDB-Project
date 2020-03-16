package com.e.omdb_android_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.omdb_android_project.BaseActivity;
import com.e.omdb_android_project.R;
import com.e.omdb_android_project.model.reponse.FilmItem;
import com.e.omdb_android_project.ui.MainVievModel;
import com.e.omdb_android_project.ui.adapter.FilmListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.editSearch)
    AppCompatEditText editSearch;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.recList)
    RecyclerView recList;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.tvAlgorithm)
    TextView tvAlgorithm;

    MainVievModel mainVievModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainVievModel = ViewModelProviders.of(this).get(MainVievModel.class);

        tvAlgorithm.setOnClickListener(v -> {
            startActivity(new Intent(this, AlgorithmActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            );
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        ivSearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editSearch.getText().toString())) {
                mainVievModel.callApi(editSearch.getText().toString());
                progressDialog.show();
            }
        });

        mainVievModel.filmLiveData.observe(this, filmItem -> {
            progressDialog.dismiss();
            if (filmItem.getResponse().equals("True")) {
                tvMessage.setVisibility(View.GONE);
                recList.setVisibility(View.VISIBLE);

                List<FilmItem> filmList = new ArrayList<>();
                filmList.add(filmItem);

                FilmListAdapter adapter = new FilmListAdapter(MainActivity.this, filmList);
                recList.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                recList.setAdapter(adapter);
            } else {
                tvMessage.setVisibility(View.VISIBLE);
                recList.setVisibility(View.GONE);
                tvMessage.setText(filmItem.getError());
            }
        });

    }


}
