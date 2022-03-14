package com.example.myapplication;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.myapplication.models.User;
import com.example.myapplication.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private MediatorLiveData<AuthResource<User>> cacheUser = new MediatorLiveData<>();

    @Inject
    public SessionManager () {

    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if (cacheUser != null) {
            cacheUser.setValue(AuthResource.loading(null));
            cacheUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cacheUser.setValue(userAuthResource);
                    cacheUser.removeSource(source);
                }
            });
        }
    }

    public void logOut() {
        Log.e("amir","logging out...");
        cacheUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cacheUser;
    }
}
