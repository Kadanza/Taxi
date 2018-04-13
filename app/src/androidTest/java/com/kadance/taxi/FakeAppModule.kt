package com.kadance.taxi

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import com.kadance.taxi.common.repo.IDataRepo
import com.kadance.taxi.common.repo.NetRepo
import com.kadance.taxi.net.GoogleServicerApi
import dagger.Provides

/**
 * Created by Kenza on 11.04.2018.
 */
open class  FakeAppModule(override val context: Context) : AppModule(context){

//    override fun pointsLD(repo: IDataRepo): PointsLD { return FakePointsLD(repo) }



    override fun userCoordinatLD(flp: FusedLocationProviderClient): UserLocationLD {
        return FakeUserLocationLD(flp)
    }

//    override fun netRepo( server : GoogleServicerApi): NetRepo {
//        return FakeNetRepo(server)
//    }

}