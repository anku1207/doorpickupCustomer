package com.uav.doorpickup.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uav.doorpickup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout textInputLayout=findViewById(R.id.textInputLayout);

        textInputLayout.setError("sdfsfdsdf");
        textInputLayout.setErrorEnabled(true);
    }
}
