package com.kadance.taxi.common.repo

import com.kadance.taxi.net.GoogleServicerApi
import com.kadance.taxi.net.LocationResult
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Kenza on 12.04.2018.
 */
open class NetRepo @Inject constructor(val server :GoogleServicerApi) {

    open fun requestLocationByAddress(addr : String): Observable<LocationResult> {
        return server.getLocationByAddress(addr)
    }

}