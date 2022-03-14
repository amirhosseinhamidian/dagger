package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {
        sessionManager.getAuthUser().observe(this,userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case LOADING:{
                        break;
                    }

                    case AUTHENTICATED:{
                        Toast.makeText(this,userAuthResource.data.getEmail(),Toast.LENGTH_LONG).show();
                        break;
                    }

                    case ERROR:{
                        Toast.makeText(this,userAuthResource.message,Toast.LENGTH_LONG).show();
                        break;
                    }

                    case NOT_AUTHENTICATED:{
                        navLoginScreen();
                        break;
                    }
                }
            }
        });
    }

    private void navLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
