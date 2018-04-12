package com.kadance.taxi.common.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.google.android.gms.maps.SupportMapFragment
import com.jakewharton.rxbinding2.view.RxView
import com.kadance.taxi.R
import com.kadance.taxi.app.d
import com.kadance.taxi.app.e
import com.kadance.taxi.common.live.PointEventLD
import com.kadance.taxi.common.live.PointEventLD.Status.*
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.view.DetailActivity.ContentState.*
import com.kadance.taxi.common.view.activity.BaseActivity
import com.kadance.taxi.common.view.adapter.RPointsAdapter
import com.kadance.taxi.data.RPoint
import com.kadance.taxi.net.GoogleServicerApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_detail.*
import java.net.URLEncoder
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
class DetailActivity : BaseActivity(), RPointsAdapter.RPointDelegate, CreatePointDialog.CreatePointDelegate {



    enum  class  ContentState {
        Loading, Content, Empty
    }


    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    @Inject lateinit var pointsLD: PointsLD
    @Inject lateinit var eventLD: PointEventLD

    lateinit var presenter: DetailPresenter

    var contentState = Empty


    private var pointsData: List<RPoint> = ArrayList()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_detail)

        presenter = ViewModelProviders.of(this, modelFactory)[DetailPresenter::class.java]
        setSupportActionBar(toolbar as Toolbar?)


        val adapter = RPointsAdapter(this)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        pointsLD.observe(this, Observer {

            it?.let {
                pointsData = it
                recycler.adapter.notifyDataSetChanged()

                checkContent()
                updateContentState()
            }
        })


        eventLD.observe(this, Observer {

            when(it){
                Succes -> { }
                NoInternet -> { }
                Error -> {}
                Nope -> {}
                null -> {}
            }
        })


        RxView.clicks(fab).subscribe({
            openCreatePointDialog()
        })
    }

    @SuppressLint("CheckResult")
    fun openCreatePointDialog(){

        val dialog = CreatePointDialog(this, this)
        dialog.show()



    }





    fun checkContent(){
        if(contentState != Loading){
            if(pointsData.isNotEmpty()) contentState = Content else contentState = Empty
        }
    }



    fun updateContentState(){

        content.visibility = View.GONE
        empty.visibility = View.GONE
        loading.visibility = View.GONE

        when(contentState){

            Loading ->   loading.visibility = View.VISIBLE
            Content ->   content.visibility = View.VISIBLE
            Empty   ->   empty.visibility = View.VISIBLE
        }
    }


    /**
     *  CreatePointDialog callbacks
     */



    override fun onPointDialogFind() {

    }

    override fun onPointDialogCreate() {

    }

    override fun onPointDialogCancel() {}




    /**
     *  RPointsAdapter callbacks
     */

    override fun onPointsRequested(): List<RPoint> {
        return pointsData
    }

    override fun onEditPoint(point: RPoint) {
    }

    override fun onRemovePoint(point: RPoint) {
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}