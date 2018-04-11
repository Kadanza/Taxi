package com.kadance.taxi.app.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserCoordinatLD
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
open class AppModule(open val context: Context)  {


    @Provides
    fun provideContext(): Context { return context }

    @Provides
    fun provideRealm(): Realm {
        Realm.init(context)
        val bdName = "realm.bd"
        val mConfig = RealmConfiguration.Builder()
                .schemaVersion(3)
                .name(bdName)
                .build()

        return Realm.getInstance(mConfig)
    }


    @Provides
    open fun providePointsLD(): PointsLD { return PointsLD() }

    @Provides
    fun provideUserCoordinatLD( flp : FusedLocationProviderClient ): UserCoordinatLD { return UserCoordinatLD(flp) }


    @Provides
    fun provideFusedLocationProviderClient(): FusedLocationProviderClient { return   LocationServices.getFusedLocationProviderClient(context) }










}