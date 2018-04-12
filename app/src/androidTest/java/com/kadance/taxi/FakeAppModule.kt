package com.kadance.taxi

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import dagger.Provides

/**
 * Created by Kenza on 11.04.2018.
 */
class  FakeAppModule(override val context: Context) : AppModule(context){

    override fun providePointsLD(): PointsLD { return FakePointsLD() }



    override fun provideUserCoordinatLD(flp: FusedLocationProviderClient): UserLocationLD {
        return FakeUserLocationLD(flp)
    }

}