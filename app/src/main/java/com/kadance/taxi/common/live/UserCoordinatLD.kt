package com.kadance.taxi.common.live

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
class UserCoordinatLD @Inject constructor(val fusedLocationClient : FusedLocationProviderClient)  :  LiveData<Int>() {




    init {



        value = 3
    }


    var x = 0
    override fun onActive() {
        super.onActive()

//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            PermissionsUtils.requestPermission(context, Manifest.permission.ACCESS_FINE_LOCATION, request_id)
//            PermissionsUtils.requestPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION, request_id)
//            return
//        }

//        fusedLocationClient.lastLocation.addOnSuccessListener {  }




    }

    override fun onInactive() {
        super.onInactive()
    }




}