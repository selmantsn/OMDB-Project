package com.e.omdb_android_project.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.e.omdb_android_project.BaseActivity;
import com.e.omdb_android_project.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlgorithmActivity extends BaseActivity {

    String data = "abbcccaaeeeeb bfffffca ccab";

    @BindView(R.id.btnRun)
    Button btnRun;
    @BindView(R.id.editStringData)
    EditText editStringData;
    @BindView(R.id.editNValue)
    EditText editNValue;
    @BindView(R.id.tvResult)
    TextView tvResult;


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        ButterKnife.bind(this);


        editStringData.setText(data);

        btnRun.setOnClickListener(v -> {
            runReplace(editStringData.getText().toString(), Integer.parseInt(editNValue.getText().toString()));

        });

        //run(data, n);
    }

    private void run(String data, int nCount) {
        StringBuilder sbData = new StringBuilder(data);
        List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < sbData.length() - 1; i++) {
            if (sbData.charAt(i) == sbData.charAt(i + 1)) {
                indexList.add(i);
            } else {
                if (indexList.size() >= nCount) {
                    for (int j = 0; j < indexList.size(); j++) {
                        sbData.setCharAt(indexList.get(j), '*');
                    }
                    indexList.clear();
                }
            }
        }
        System.out.println(sbData);
    }


    private void runReplace(String data, int n) {
        int repeat = 1;
        StringBuilder sbData = new StringBuilder(data);
        int length = sbData.length();
        for (int i = 1; i < sbData.length(); i++) {
            if (i == length)
                break;

            char now = sbData.charAt(i - 1);
            char after = sbData.charAt(i);
            if (now == after) {
                repeat = repeat + 1;
            } else {
                repeat = 1;
            }
            if (repeat >= n) {
                if (i + 1 < length) {
                    if (after == sbData.charAt(i + 1)) {
                        continue;
                    }
                    for (int j = 0; j < i; j++) {
                        if (i - j == 1)
                            sbData.setCharAt(0, '*');
                        if (sbData.charAt(i - j) == after)
                            sbData.setCharAt(i - j, '*');
                        else
                            break;

                        tvResult.setText(sbData);
                    }

                }
            }

        }
    }




}
