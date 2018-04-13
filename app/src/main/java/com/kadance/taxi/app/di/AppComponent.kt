package com.kadance.taxi.app.di

import com.kadance.taxi.app.di.module.ActivityModule
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.app.di.module.ViewModelModule
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.presenter.MapPresenter
import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.kit.LogKit
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

interface AppComponent : AndroidInjector<DaggerApplication> {


    fun getMainPresenter(): MapPresenter
    fun getDetailPresenter(): DetailPresenter
    fun getLogKit(): LogKit



}


