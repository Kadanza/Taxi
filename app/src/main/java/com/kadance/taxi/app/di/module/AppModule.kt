package com.kadance.taxi.app.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kadance.taxi.common.live.PointEventLD
import com.kadance.taxi.kit.PermissionKit
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import com.kadance.taxi.common.repo.NetRepo
import com.kadance.taxi.kit.LogKit
import com.kadance.taxi.net.GoogleServicerApi
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
open class AppModule(open val context: Context) {


    @Provides
    fun provideContext(): Context {
        return context
    }

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
    open fun providePointsLD(): PointsLD {
        return PointsLD()
    }

    @Provides
    open fun providePointEventLD(): PointEventLD {
        return PointEventLD()
    }


    @Provides
    open fun provideUserCoordinatLD(flp: FusedLocationProviderClient): UserLocationLD {
        return UserLocationLD(flp)
    }


    @Provides
    fun provideFusedLocationProviderClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }




    @Provides
    fun providePermissionsKit(): PermissionKit {
        return PermissionKit()
    }

    @Provides
    fun provideLogKit(): LogKit {
        return LogKit()
    }

    @Provides
    fun provideGoogleServiceApi(): GoogleServicerApi {
        return GoogleServicerApi.create()
    }


    @Provides
    fun provideNetRepo(server: GoogleServicerApi): NetRepo {
        return NetRepo(server)
    }






}