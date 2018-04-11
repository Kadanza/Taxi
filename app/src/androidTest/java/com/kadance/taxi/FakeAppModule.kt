package com.kadance.taxi

import android.content.Context
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.common.live.PointsLD
import dagger.Provides

/**
 * Created by Kenza on 11.04.2018.
 */
class  FakeAppModule(override val context: Context) : AppModule(context){

    override fun providePointsLD(): PointsLD { return FakePointsLD() }

}