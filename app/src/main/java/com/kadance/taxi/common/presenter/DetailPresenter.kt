package com.kadance.taxi.common.presenter

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.kadance.taxi.app.d
import com.kadance.taxi.app.e
import com.kadance.taxi.common.live.PointEventLD
import com.kadance.taxi.common.repo.NetRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */


open  class DetailPresenter @Inject constructor(
        val pointEventLD: PointEventLD,
        val netRepo: NetRepo ) : ViewModel() {


    @SuppressLint("CheckResult")
    open fun createPointByAddress(address : String){

        netRepo.requestLocationByAdress(address)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    d(it.toString())

                    val lat = it.results?.first()?.geometry?.location?.lat   ?: 0.0
                    val lng = it.results?.first()?.geometry?.location?.lng  ?: 0.0
                    val name =  address



                    savePoint(name, lat, lng)
                },{
                    if(it is TimeoutException){
                        pointEventLD.setEvent( PointEventLD.Status.NoInternet)
                    }else{
                        pointEventLD.setEvent( PointEventLD.Status.Error)
                    }
                    e("", Exception(it))
                })
    }




    fun savePoint(name: String, lat: Double, lng: Double) {



    }



    open fun createPointByLatLng(){

    }

}