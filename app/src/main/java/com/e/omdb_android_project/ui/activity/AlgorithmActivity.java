package com.e.omdb_android_project.ui.activity;

import android.os.Bundle;
import android.util.Log;
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
            tvResult.setText(runRegexJ(editStringData.getText().toString(), Integer.parseInt(editNValue.getText().toString())));
        });

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


    private String runRegexJ(String data, int n) {

        String[] stringArray = data.split("(?<=(.))(?!\\1|$)");
        String newString = new String();


        for (int i = 0; i < stringArray.length; i++) {
            Log.i("TAGF", "runRegexJ: " + stringArray[i]);
            if (stringArray[i].length() >= n) {
                newString += repeater(stringArray[i].length());
            } else {
                newString += stringArray[i];
            }
        }
        return newString;
    }


    //Kullanılan java versiyonunda repeat fonksiyonu olmadı için kullanıldı
    private String repeater(int length) {
        Log.i("TAGF", "length: " + length);
        String empty = new String();
        for (int i = 0; i < length; i++) {
            empty += "*";

        }
        Log.i("TAGF", "Empty : repeater: " + empty);
        return empty;
    }


}
