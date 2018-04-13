package com.kadance.taxi.common.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.kadance.taxi.R
import com.kadance.taxi.app.parseToDouble
import com.kadance.taxi.common.view.PointDialog.Status.*
import com.kadance.taxi.common.view.PointDialog.Type.*
import kotlinx.android.synthetic.main.dlg_add_point.*
import com.kadance.taxi.data.RPoint
import com.kadance.taxi.kit.HideSoftKeyboard


/**
 * Created by Kenza on 12.04.2018.
 */

class PointDialog(context: Context, val delegate : CreatePointDelegate, val  type : Type, val rPoint: RPoint? = null) : Dialog(context) {


    enum class  Type{
        Create, Modify
    }


    interface  CreatePointDelegate{

        fun onPointDialogCancel()
        fun onPointDialogFind(address: String)
        fun onPointDialogCreate(address: String, lat: Double, lng: Double)
        fun onPointDialogUpdate(point : RPoint, address: String, lat: Double, lng: Double)
    }

    enum class Status{
        Manually, Empty
    }

    var status = Empty

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dlg_add_point)

        manuallyCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) status = Manually else  status = Empty
            updateUI()
        }





        RxView.clicks(findBV).subscribe({

            val address = nameED.text.toString()
            if(address.isEmpty()) return@subscribe

            delegate.onPointDialogFind(address)
            dismiss()
        })

        RxView.clicks(createBV).subscribe({

            val address = nameED.text.toString()
            val lat = latED.text?.toString()?.parseToDouble()!!
            val lng = lngED.text?.toString()?.parseToDouble()!!

            delegate.onPointDialogCreate(address, lat, lng)
            dismiss()
        })

        RxView.clicks(cancelBV).subscribe({
            delegate.onPointDialogCancel()
            dismiss()
        })

        RxView.clicks(updateBV).subscribe({

            val address = nameED.text.toString()
            val lat = latED.text?.toString()?.parseToDouble()!!
            val lng = lngED.text?.toString()?.parseToDouble()!!

            delegate.onPointDialogUpdate(rPoint!!,address, lat, lng)
            dismiss()
        })


        updateUI()
        HideSoftKeyboard.softHide(root, this)
    }



    fun updateUI(){

        when(type){

            Create -> {
                updateBV.visibility = View.GONE

            }
            Modify -> {
                val titleStr = context.getString(R.string.update_point)

                nameED.setText( rPoint?.title ?: "")
                latED.setText( rPoint?.lat?.toString() ?: "")
                lngED.setText( rPoint?.lng?.toString() ?: "")



                title.text = titleStr
                manuallyCB.visibility = View.GONE
                createBV.visibility = View.GONE
                findBV.visibility = View.GONE
                return
            }
        }


        latContainer.visibility = View.GONE
        lngContainer.visibility = View.GONE

        findBV.visibility = View.GONE
        createBV.visibility = View.GONE





        when(status){

            Manually -> {
                latContainer.visibility = View.VISIBLE
                lngContainer.visibility = View.VISIBLE
                createBV.visibility = View.VISIBLE
            }

            Empty ->  {
                findBV.visibility = View.VISIBLE
            }
        }

    }


}