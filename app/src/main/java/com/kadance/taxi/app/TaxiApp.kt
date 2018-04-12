package com.kadance.taxi.app

import com.arranlomas.daggerviewmodelhelper.AppInjector
import com.kadance.taxi.app.di.AppComponent
import com.kadance.taxi.app.di.DaggerAppComponent
import com.kadance.taxi.app.di.module.AppModule
import dagger.android.AndroidInjector

import dagger.android.support.DaggerApplication

class TaxiApp : DaggerApplication(){


    companion object  {
        var appComponent: AppComponent? = null

    }

    public override fun applicationInjector(): AndroidInjector<DaggerApplication> {

        AppInjector.init(this)
        val module  = AppModule(this)
        appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent?.inject(this)
        return appComponent!!

    }


    override  fun onCreate() {
        super.onCreate()
    }
}