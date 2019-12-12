package com.uav.doorpickup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

public class Base_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.w("onCreate","onCreate");

        //manoj

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.w("onStart","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("onResume","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("onPause","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("onStop","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("onRestart","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("onDestroy","onDestroy");
    }


}
