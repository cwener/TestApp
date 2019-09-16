package com.example.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.anno.annotation.BindIntent;
import com.example.anno.process.Anno;

/**
 * Created by chengwen on 2019-07-06
 */
public class AnnoFragment extends Fragment {
    @BindIntent("fragmentTest")
    String testString;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Anno.bind(this, getArguments());
    }
}
