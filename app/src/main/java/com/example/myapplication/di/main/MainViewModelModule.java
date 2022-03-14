package com.example.myapplication.di.main;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.di.ViewModelKey;
import com.example.myapplication.ui.main.posts.PostViewModel;
import com.example.myapplication.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostViewModel(PostViewModel viewModel);
}
