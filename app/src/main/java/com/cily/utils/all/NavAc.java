package com.cily.utils.all;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

//import com.cily.utils.app.ac.BaseActivity;


public class NavAc extends Activity {
    private final int MAX = 10;

    int num = 0;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_nav);
//
//        findBtn(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
