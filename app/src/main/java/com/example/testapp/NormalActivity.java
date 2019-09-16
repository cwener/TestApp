package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chengwen on 2019-07-06
 */
public class NormalActivity extends AppCompatActivity {
    String mTestString;
    boolean mTestBoolean;
    int mTestInt;
    float mTestFloat;
    double mTestDouble;
    byte mTestByte;
    char mTestChar;
    CloudMeta mTestObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent != null) {
            mTestString = intent.getStringExtra("intentString");
            mTestBoolean = intent.getBooleanExtra("intentBoolean", true);
            mTestInt = intent.getIntExtra("intentInt", 0);
            mTestFloat = intent.getFloatExtra("intentFloat", 0);
            mTestDouble = intent.getDoubleExtra("intentDouble", 0);
            mTestObject = (CloudMeta) intent.getSerializableExtra("intentObject");
        }
    }
}
