package com.example.testapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anno.annotation.BindIntent;
import com.example.anno.process.Anno;

/**
 * Created by chengwen on 2019-07-03
 */
public class AnnoActivity extends AppCompatActivity {
    @BindIntent("intentString")
    String mTestString;
    @BindIntent("intentBoolean")
    boolean mTestBoolean;
    @BindIntent("intentInt")
    int mTestInt;
    @BindIntent("intentFloat")
    float mTestFloat;
    @BindIntent("intentDouble")
    double mTestDouble;
    @BindIntent("intentByte")
    byte mTestByte;
    @BindIntent("intentChar")
    char mTestChar;
    @BindIntent("intentObject")
    CloudMeta mTestObject;

    RelativeLayout mRelativeLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long startTime = SystemClock.currentThreadTimeMillis();
        Anno.bind(this);
        long endTime = SystemClock.currentThreadTimeMillis();
        Log.i("time", endTime - startTime + "");
        mTextView = findViewById(R.id.text);
        mTextView.setText(mTestInt + "   +" + mTestDouble);





        mRelativeLayout = findViewById(R.id.container);
        Fragment  fragment = new AnnoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentTest", "fragmentSuc");
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.realContainer, fragment).commitNow();
    }
}
