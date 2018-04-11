package com.kadance.taxi.app.di

import com.kadance.taxi.app.di.module.ActivityModule
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.app.di.module.ViewModelModule
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.presenter.MapPresenter
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        // DependencyProviderModule::class,
        AppModule::class,
        ViewModelModule::class))

interface AppComponent2 : AndroidInjector<DaggerApplication> {


    fun getMainPresenter(): MapPresenter
    fun getDetailPresenter(): DetailPresenter


}


