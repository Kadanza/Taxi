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
        var testible = false
    }


    fun setComponent(component: AppComponent){
        //AppInjector.init(this)
        TaxiApp.appComponent = component
        appComponent?.inject(this)

    }

    public override fun applicationInjector(): AndroidInjector<DaggerApplication> {

        val module  = AppModule(this)
        appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent?.inject(this)
        return appComponent!!

    }


    override  fun onCreate() {
        AppInjector.init(this)
        super.onCreate()
    }
}