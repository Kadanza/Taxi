package com.kadance.taxi.common.live

import android.arch.lifecycle.LiveData
import com.kadance.taxi.data.RPoint
import javax.inject.Inject

/**
 * Created by Kenza on 12.04.2018.
 */
open class PointEventLD  @Inject constructor()  :  LiveData<PointEventLD.Status>(){


    enum class Status{
        Succes, NoInternet, Error, Nope
    }

    init {
        value = Status.Nope
    }

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    fun setEvent(event: PointEventLD.Status) {
        value = event
    }


}