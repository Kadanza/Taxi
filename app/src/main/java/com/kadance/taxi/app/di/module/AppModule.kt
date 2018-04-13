package com.kadance.taxi.app.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kadance.taxi.kit.PermissionKit
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import com.kadance.taxi.common.repo.IDataRepo
import com.kadance.taxi.common.repo.NetRepo
import com.kadance.taxi.common.repo.RealmRepo
import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.data.PointDAO
import com.kadance.taxi.kit.LogKit
import com.kadance.taxi.net.GoogleServicerApi
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
open class AppModule(open val context: Context) {


    @Provides
    fun provideContext(): Context {
        return context
    }


//    @Singleton
    @Provides
    fun realm(): Realm {
        Realm.init(context)
        val bdName = "realm.bd"
        val mConfig = RealmConfiguration.Builder()
                .schemaVersion(3)
                .name(bdName)
                .build()

        return Realm.getInstance(mConfig)
    }


    @Provides
    open fun  pointsLD(repo: IDataRepo): PointsLD {
        return PointsLD(repo)
    }

    @Provides
    open fun isLoadingLD(): DetailActivity.IsLoadingLD {
        return  DetailActivity.IsLoadingLD()
    }


    @Provides
    open fun userCoordinatLD(flp: FusedLocationProviderClient): UserLocationLD {
        return UserLocationLD(flp)
    }


    @Provides
    fun fusedLocationProviderClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }




    @Provides
    fun permissionsKit(): PermissionKit {
        return PermissionKit()
    }

    @Provides
    fun logKit(): LogKit {
        return LogKit()
    }

    @Provides
    fun googleServiceApi(): GoogleServicerApi {
        return GoogleServicerApi.create()
    }


    @Provides
    fun netRepo(server: GoogleServicerApi): NetRepo {
        return NetRepo(server)
    }

    @Provides
    fun dataRepo( realm: Realm , pointDao : PointDAO): IDataRepo {
        return RealmRepo(realm, pointDao)
    }


    @Provides
    fun poinDao(realm: Realm): PointDAO {
        return PointDAO(realm)
    }






}