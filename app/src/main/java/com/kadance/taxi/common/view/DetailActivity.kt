package com.kadance.taxi.common.view

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.kadance.taxi.R
import com.kadance.taxi.app.d
import com.kadance.taxi.app.showToast
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.view.DetailActivity.ContentState.*
import com.kadance.taxi.common.view.activity.BaseActivity
import com.kadance.taxi.common.view.adapter.RPointsAdapter
import com.kadance.taxi.data.RPoint
import kotlinx.android.synthetic.main.act_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe



/**
 * Created by Kenza on 11.04.2018.
 */
class DetailActivity : BaseActivity(), RPointsAdapter.RPointDelegate, PointDialog.CreatePointDelegate {



    enum  class  ContentState {
        Loading, Content, Empty, NoLoading
    }

    enum  class  PointLoadingEvent {
        Loading, Create, Empty, Timeout, Error
    }


    /** Show loading progress bar  */

    open class IsLoadingLD  @Inject constructor()  :  LiveData<Boolean>(){
        init { value = false }

        fun setLoading(value: Boolean?) {
            super.postValue(value!!)
        }
    }

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var pointsLD: PointsLD
    @Inject lateinit var isLoadingLD: IsLoadingLD

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
       // updateContentState()


        pointsLD.observe(this, Observer {

            it?.let {
                pointsData = it
                recycler.adapter.notifyDataSetChanged()

                if(contentState == Loading) return@Observer

                if( !pointsData.isEmpty()){
                    contentState = Content
                }else{
                    contentState = Empty
                }
                updateContentState(contentState)
            }
        })


        isLoadingLD.observe(this, Observer {

           if(it!!){
               contentState = Loading
           }else{
               contentState = NoLoading
           }
            updateContentState(contentState)
        })

        //isLoadingLD.setLoading(true)

        RxView.clicks(fab).subscribe({
            openCreatePointDialog()
        })
    }

    fun openCreatePointDialog(){
        val dialog = PointDialog(this, this, PointDialog.Type.Create)
        dialog.show()
    }








    @Synchronized fun updateContentState( contentState : ContentState){

        async(UI) {
            content.visibility = View.GONE
            empty.visibility = View.GONE
            loading.visibility = View.GONE

            when(contentState){

                Loading ->  {
                    loading.visibility = View.VISIBLE
                }
                Content ->   {
                    content.visibility = View.VISIBLE
                }
                Empty   ->   {
                    empty.visibility = View.VISIBLE
                }
                NoLoading -> {
                    if(pointsData.isNotEmpty())
                        content.visibility = View.VISIBLE
                    else
                        empty.visibility = View.VISIBLE
                }
            }
            d("")
        }
    }



    /** Event loading point location by address */

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: PointLoadingEvent) {
        d(event.name)
        when(event){

            PointLoadingEvent.Loading -> {}
            PointLoadingEvent.Create -> {
                val msg = getString(R.string.point_created)
                showToast( msg )
            }
            PointLoadingEvent.Empty -> {
                val emsg = getString(R.string.no_res_for_point_location)
                showToast( emsg )
            }
            PointLoadingEvent.Timeout ->  {
                val emsg = getString(R.string.connection_timeout)
                showToast( emsg )
            }
            PointLoadingEvent.Error -> {
                val emsg = getString(R.string.error_during_loading_point)
                showToast( emsg )
            }
        }

    }



    /**
     *  PointDialog callbacks
     */



    override fun onPointDialogFind(address : String) {
        presenter.createPointByAddress(address)
        pointsLD.update()
    }

    override fun onPointDialogCreate(address : String, lat: Double, lng :Double) {
        presenter.savePoint(address, lat, lng)
        pointsLD.update()
    }

    override fun onPointDialogCancel() {

    }

    override fun onPointDialogUpdate(point: RPoint, address: String, lat: Double, lng: Double) {
        presenter.updatePoint(point, address, lat, lng)
        pointsLD.update()
    }




    /**
     *  RPointsAdapter callbacks
     */

    override fun onPointsRequested(): List<RPoint> {
        return pointsData
    }

    override fun onEditPoint(point: RPoint) {
        PointDialog(this, this ,  PointDialog.Type.Modify, point).show()

    }


    override fun onRemovePoint(point: RPoint) {
        presenter.removePoint(point)
        pointsLD.update()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    /**
     * Lifecircle activity
     */
    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }




}