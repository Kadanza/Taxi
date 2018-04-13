package com.kadance.taxi.common.live

import android.arch.lifecycle.LiveData
import com.kadance.taxi.app.d
import com.kadance.taxi.common.repo.IDataRepo
import com.kadance.taxi.data.PointDAO
import com.kadance.taxi.data.RPoint
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
open class PointsLD  @Inject constructor(var  dataRepo : IDataRepo)  :  LiveData< List<RPoint> >() {




    init {
        d("create new pointLD ")

        dataRepo.getAllPoints().addChangeListener { t, changeSet ->
            postValue( t)
        }

        value = dataRepo.getAllPoints()
    }


    var x = 0
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    fun update() {
        val data = dataRepo.getAllPoints()
        value =  data
    }


}