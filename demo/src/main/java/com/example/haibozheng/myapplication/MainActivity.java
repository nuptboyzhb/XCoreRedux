package com.example.haibozheng.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.haibozheng.myapplication.components.container.MainPageUIComponent;

public class MainActivity extends AppCompatActivity {

    private MainPageUIComponent mMainPageUIComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPageUIComponent = new MainPageUIComponent(this);
        setContentView(mMainPageUIComponent);
    }
}
