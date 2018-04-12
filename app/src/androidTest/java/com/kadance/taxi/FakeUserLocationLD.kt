package com.kadance.taxi

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.kadance.taxi.common.live.UserLocationLD
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by Kenza on 12.04.2018.
 */

class FakeUserLocationLD (fusedLocationClient: FusedLocationProviderClient) : UserLocationLD(fusedLocationClient) {


    var dif = 0.0

    private var job: Deferred<Unit>? = null

    override fun onActive() {
       job =  async {

           while (true){
               val location = Location("")
               location.latitude = 47.23 + dif
               location.longitude = 39.7 - dif

               dif += 0.1

               postValue(location)

               delay(1000)

           }


        }

        job?.start()
    }

    override fun onInactive() {
        job?.cancel()
    }

}