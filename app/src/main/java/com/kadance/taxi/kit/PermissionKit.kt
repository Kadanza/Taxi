package com.kadance.taxi.kit

import android.Manifest
import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.kadance.taxi.common.view.activity.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Kenza on 12.04.2018.
 */
class PermissionKit  @Inject constructor() {


    private var rxPermissions: RxPermissions? = null


    fun setActivity(activity : Activity){
        rxPermissions = RxPermissions(activity)
    }


    fun checkLocationPermission(): Observable<Boolean>? {
            return  rxPermissions?.request( Manifest.permission.ACCESS_COARSE_LOCATION)
    }





}