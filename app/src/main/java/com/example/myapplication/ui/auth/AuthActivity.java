package com.example.myapplication.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.myapplication.R;
import com.example.myapplication.ui.auth.AuthViewModel;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.vm.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    private ImageView imageView;
    private EditText etUserId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        imageView = findViewById(R.id.ivLogo);
        etUserId = findViewById(R.id.etUserId);
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.btnLogin).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        requestManager.load(logo).into(imageView);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observeAuthState().observe(this,userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case LOADING:{
                        showProgressBar(true);
                        break;
                    }

                    case AUTHENTICATED:{
                        showProgressBar(false);
                        onLoginSuccess();
                        break;
                    }

                    case ERROR:{
                        showProgressBar(false);
                        Toast.makeText(this,userAuthResource.message,Toast.LENGTH_LONG).show();
                        break;
                    }

                    case NOT_AUTHENTICATED:{
                        showProgressBar(false);
                        break;
                    }
                }
            }
        });
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:{
                attemptLogin();
                break;
            }

        }
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(etUserId.getText().toString())){
            Toast.makeText(this,"user id cant empty",Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.authenticateWithId(Integer.parseInt(etUserId.getText().toString()));
    }
}