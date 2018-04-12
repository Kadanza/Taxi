package com.kadance.taxi.common.live

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject
import android.Manifest.permission
import android.location.Location
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.kadance.taxi.app.d
import com.kadance.taxi.app.w
import java.lang.Exception
import android.os.Looper
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.kadance.taxi.app.i
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager




/**
 * Created by Kenza on 11.04.2018.
 */
open class UserLocationLD @Inject constructor(val fusedLocationClient : FusedLocationProviderClient)  :  LiveData<Location>(), OnSuccessListener<Location>, OnFailureListener {

    override fun onFailure(e: Exception) {
        w("failure get loсation", e)
    }


    override fun onSuccess(local : Location?) {
        d("obtain location ${local.toString()}")
        value = local
    }


    init {
        //value = 3
    }


    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation.addOnSuccessListener (this)
        fusedLocationClient.lastLocation.addOnFailureListener (this)

        val locationRequest = LocationRequest.create()
        locationRequest.setFastestInterval(10000)
//        locationRequest.setInterval(10000)

        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult?) {

                i("obtain current location")
                d(" $locationResult")

                val currentLocation = locationResult!!.lastLocation as Location
                val result = "Current Location Latitude is " +
                        currentLocation.getLatitude() + "\n" +
                        "Current location Longitude is " + currentLocation.getLongitude()

            }
        }, Looper.myLooper())
    }





    override fun onInactive() {
        super.onInactive()
    }







}