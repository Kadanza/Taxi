package com.kadance.taxi.common.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.kadance.taxi.R
import com.kadance.taxi.common.presenter.MapPresenter
import com.kadance.taxi.common.view.activity.BaseActivity
import javax.inject.Inject
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import kotlinx.android.synthetic.main.act_map.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.kadance.taxi.app.d
import com.kadance.taxi.app.i
import com.kadance.taxi.kit.PermissionKit


class MapActivity : BaseActivity(), OnMapReadyCallback {


    /**
     * Переход по клику на лист
     *
     * Загрузка точек из бд
     *
     * Определение местоположения юзера
     *
     *
     *
     */

    val  MAP_ZOOM = 6.0f


    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var pointsLD: PointsLD
    @Inject lateinit var userLocationLD: UserLocationLD
    @Inject lateinit var permissionKit : PermissionKit

    lateinit var presenter: MapPresenter


    private var userPositionMarker: Marker? = null
    private var mGoogleMap: GoogleMap? = null
    private var latLngForCamera: LatLng? = null
    private var isCamaraMovedOnce = false





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        i("create map activity")

        setContentView(R.layout.act_map)
        presenter = ViewModelProviders.of(this, modelFactory)[MapPresenter::class.java]

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setSupportActionBar(toolbar as Toolbar?)
    }


    fun openDetail(){
        i("open detail activity")
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }




    fun prepareUserLocationLD(){

        userLocationLD.observe(this, Observer{ point->

            if(point == null){
                moveCamaraOnce()
            }
            point.let {it!!

                d("create point of user's location lat = ${it.latitude} lng = ${it.longitude} ")

                val title = getString(R.string.my_location)
                val latLng = LatLng(it.latitude, it.longitude)

                val icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_my_location)


                if( userPositionMarker == null){
                    val mark  = MarkerOptions().icon(icon).position(latLng).title(title)
                    userPositionMarker = mGoogleMap?.addMarker(mark)
                }
                userPositionMarker?.let {
                    // execute this block if not null
                    d("qwe2")
                } ?: run {
                    // execute this block if null
                    d("qwe")
                }


                userPositionMarker?.position = latLng


                latLngForCamera = latLng
                moveCamaraOnce()
            }
        })
    }


    private fun moveCamaraOnce(){

        latLngForCamera.let {
            if(!isCamaraMovedOnce){

                d("move camera")
                mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, MAP_ZOOM))
                isCamaraMovedOnce = true
            }
        }
    }





    @SuppressLint("CheckResult")
    override fun onMapReady(map: GoogleMap?) {

           mGoogleMap = map

        i("map ready")

        permissionKit.setActivity(this)
        permissionKit.checkLocationPermission()?.subscribe({

            if(it){
                i("location permission is granted")
                prepareUserLocationLD()
            }else{
                i("location permission is denied ")
            }
        })

        pointsLD.observe(this, Observer{

            d("get ${it?.size} points")

            for(point in it!!){

                val latLng = LatLng(point.lat, point.lng)
                val title = point.title
                map?.addMarker(MarkerOptions().position(latLng).title(title))

                latLngForCamera = latLng

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_detail) {
            openDetail()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}