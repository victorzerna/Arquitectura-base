package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.cosmo.arquitecturamvpbase.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponets();
        validateCredentiales();
    }

    private void validateCredentiales() {

    }

    private void initComponets() {
        editTextUsername = (EditText)findViewById(R.id.edittext_username);
        editTextPassword = (EditText)findViewById(R.id.edittext_password);
    }


}
