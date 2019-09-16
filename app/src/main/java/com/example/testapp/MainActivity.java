package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myannotation.annotation.TrackName;

@TrackName(name = "测试页面")
public class MainActivity extends AppCompatActivity {


    RelativeLayout mRelativeLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = findViewById(R.id.container);
        mTextView = findViewById(R.id.text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnnoActivity.class);
                intent.putExtra("intentString", "123Suc");
                intent.putExtra("intentBoolean", true);
                intent.putExtra("intentInt", 1);
                intent.putExtra("intentFloat", 1.0f);
                intent.putExtra("intentDouble", 1.0);
                intent.putExtra("intentObject", true);
                CloudMeta meta = new CloudMeta();
                meta.setId(123);
                meta.setName("testName");
                intent.putExtra("intentObject", meta);
                startActivity(intent);
            }
        });
    }


}
