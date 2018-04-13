package com.kadance.taxi

import com.kadance.taxi.common.repo.NetRepo
import com.kadance.taxi.net.GoogleServicerApi
import com.kadance.taxi.net.LocationResult
import io.reactivex.Observable
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Kenza on 13.04.2018.
 */
class FakeNetRepo   constructor( server : GoogleServicerApi) : NetRepo(server) {

    override fun requestLocationByAddress(addr : String): Observable<LocationResult> {

        val loc = LocationResult()
        loc.status = "fake"

        val d = Observable.just(loc).delay(4000, TimeUnit.MILLISECONDS)

        return d
    }
}