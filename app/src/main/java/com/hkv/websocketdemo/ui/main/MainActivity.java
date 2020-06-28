package com.hkv.websocketdemo.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hkv.websocketdemo.DemoApp;
import com.hkv.websocketdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApp.setInForeground(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DemoApp.setInForeground(false);
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
