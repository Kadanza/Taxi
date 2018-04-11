package com.kadance.taxi.app

import com.arranlomas.daggerviewmodelhelper.AppInjector
import com.kadance.taxi.app.di.AppComponent2
import com.kadance.taxi.app.di.DaggerAppComponent2
import com.kadance.taxi.app.di.module.AppModule
import dagger.android.AndroidInjector

import dagger.android.support.DaggerApplication

class TaxiApp : DaggerApplication(){

    var appComponent: AppComponent2? = null

    public override fun applicationInjector(): AndroidInjector<DaggerApplication> {

        AppInjector.init(this)
        val module  = AppModule(this)
        appComponent = DaggerAppComponent2.builder().appModule(module).build()!!
        appComponent?.inject(this)
        return appComponent!!

    }


    override  fun onCreate() {
        super.onCreate()
    }
}