package com.kadance.taxi.common.presenter

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.kadance.taxi.app.d
import com.kadance.taxi.app.e
import com.kadance.taxi.common.repo.NetRepo
import com.kadance.taxi.common.repo.RealmRepo
import com.kadance.taxi.common.view.DetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import org.greenrobot.eventbus.EventBus



/**
 * Created by Kenza on 11.04.2018.
 */


open  class DetailPresenter @Inject constructor(
        val isLoadingLD: DetailActivity.IsLoadingLD?,
        val netRepo: NetRepo,
        val dataRepo: RealmRepo
        ) : ViewModel() {


    @SuppressLint("CheckResult")
    open fun createPointByAddress(address : String){

        isLoadingLD?.setLoading(true)

        netRepo.requestLocationByAddress(address)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    d(it.toString())

                    val lat = it.results?.first()?.geometry?.location?.lat  ?: 0.0
                    val lng = it.results?.first()?.geometry?.location?.lng  ?: 0.0
                    val name =  address

                    savePoint(name, lat, lng)
                    isLoadingLD?.setLoading(false)

                },{

                    if(it is TimeoutException){
                        EventBus.getDefault().post(DetailActivity.PointLoadingEvent.Timeout)
                    }else{
                        EventBus.getDefault().post(DetailActivity.PointLoadingEvent.Error)
                    }
                    e("Error when loading lat lng  by address", Exception(it))
                    isLoadingLD?.setLoading(false)
                })
    }




    fun savePoint(name: String, lat: Double, lng: Double) {
        dataRepo.createPoint(name, lat,lng)
        EventBus.getDefault().post(DetailActivity.PointLoadingEvent.Create)
    }



    open fun createPointByLatLng(){

    }

}