package com.hkv.websocketdemo.ui.splash;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hkv.websocketdemo.R;
import com.hkv.websocketdemo.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashFragment.SplashFragmentCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void countDownComplete() {
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }
}
