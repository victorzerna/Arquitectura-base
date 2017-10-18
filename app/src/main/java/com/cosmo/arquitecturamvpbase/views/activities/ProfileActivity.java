package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cosmo.arquitecturamvpbase.views.BaseActivity;

public class ProfileActivity extends BaseActivity<> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
