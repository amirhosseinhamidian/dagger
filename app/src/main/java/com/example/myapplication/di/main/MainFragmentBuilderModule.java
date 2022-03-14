package com.example.myapplication.di.main;

import com.example.myapplication.ui.main.posts.PostsFragment;
import com.example.myapplication.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributesPostsFragment();
}
