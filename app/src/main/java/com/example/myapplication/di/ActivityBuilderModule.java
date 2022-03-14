package com.example.myapplication.di;



import com.example.myapplication.di.auth.AuthScope;
import com.example.myapplication.di.main.MainFragmentBuilderModule;
import com.example.myapplication.di.main.MainModule;
import com.example.myapplication.di.main.MainScope;
import com.example.myapplication.di.main.MainViewModelModule;
import com.example.myapplication.ui.auth.AuthActivity;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.di.auth.AuthModule;
import com.example.myapplication.di.auth.AuthViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(modules = {
            AuthViewModelsModule.class,
            AuthModule.class
    })
    abstract AuthActivity contributesAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
                    MainViewModelModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributesMainActivity();
}
