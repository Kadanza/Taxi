package com.kadance.taxi.common.domain

import com.kadance.taxi.data.RPoint
import io.realm.RealmResults

/**
 * Created by Kenza on 11.04.2018.
 */
interface IDataRepo {

    fun getAllPoints(): RealmResults<RPoint>
    fun createPoint()
    fun editPoint()

}
