package com.example.myapplication.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.SessionManager;
import com.example.myapplication.models.User;
import com.example.myapplication.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.e("amir","profile viewModel ready...");
    }

    public LiveData<AuthResource<User>> getAuthenticateUser() {
        return sessionManager.getAuthUser();
    }
}
