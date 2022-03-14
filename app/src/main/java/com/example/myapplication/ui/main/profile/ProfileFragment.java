package com.example.myapplication.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.models.User;
import com.example.myapplication.vm.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private ProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TextView username,email,website;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(),"profile_fragment",Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);

        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticateUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticateUser().observe(getViewLifecycleOwner(),userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case AUTHENTICATED:{
                        setUserDetails(userAuthResource.data);
                        break;
                    }

                    case ERROR:{
                        setErrorDetails(userAuthResource.message);
                        break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    private void setUserDetails(User data) {
        username.setText(data.getUsername());
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
    }
}
