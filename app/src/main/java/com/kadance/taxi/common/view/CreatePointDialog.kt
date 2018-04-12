package com.kadance.taxi.common.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import android.view.View
import android.view.Window
import com.jakewharton.rxbinding2.view.RxView
import com.kadance.taxi.R
import com.kadance.taxi.common.view.CreatePointDialog.Status.*
import kotlinx.android.synthetic.main.dlg_add_point.*
import android.widget.CompoundButton
import com.kadance.taxi.kit.HideSoftKeyboard


/**
 * Created by Kenza on 12.04.2018.
 */

class CreatePointDialog(context: Context, val delegate : CreatePointDelegate) : Dialog(context) {


    interface  CreatePointDelegate{

        fun onPointDialogFind()
        fun onPointDialogCreate()
        fun onPointDialogCancel()

    }

    enum class Status{
        Manually, Empty
    }

    var status = Empty

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dlg_add_point)

        manuallyCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) status = Manually else  status = Empty
            updateUI()
        }


        RxView.clicks(findBV).subscribe({
            delegate.onPointDialogFind()
            dismiss()
        })

        RxView.clicks(createBV).subscribe({
            delegate.onPointDialogCreate()
            dismiss()
        })

        RxView.clicks(cancelBV).subscribe({
            delegate.onPointDialogCancel()
            dismiss()
        })


        updateUI()
        HideSoftKeyboard.softHide(root, this)
    }



    fun updateUI(){

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