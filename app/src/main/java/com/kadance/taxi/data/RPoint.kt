package com.kadance.taxi.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by Kenza on 11.04.2018.
 */

open class RPoint : RealmObject() {

    @PrimaryKey
    var id = UUID.randomUUID().toString()

    var lat: Double = 0.0
    var lng: Double = 0.0
    var title: String = ""
}