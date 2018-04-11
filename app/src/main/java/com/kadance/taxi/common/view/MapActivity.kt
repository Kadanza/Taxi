package com.kadance.taxi.common.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.kadance.taxi.R
import com.kadance.taxi.common.presenter.MapPresenter
import com.kadance.taxi.common.view.activity.BaseActivity
import javax.inject.Inject
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserCoordinatLD
import kotlinx.android.synthetic.main.act_map.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng




class MapActivity : BaseActivity(), OnMapReadyCallback {


    /**
     * Переход по клику на лист
     *
     * Загрузка точек из бд
     *
     * Определение местоположения юзера
     */


    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var pointsLD: PointsLD
    @Inject lateinit var userCoordinatLD: UserCoordinatLD

    lateinit var presenter: MapPresenter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_map)

        presenter = ViewModelProviders.of(this, modelFactory)[MapPresenter::class.java]

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setSupportActionBar(toolbar as Toolbar?)



    }





    fun openDetail(){
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }


    override fun onMapReady(map: GoogleMap?) {

        pointsLD.observe(this, Observer{

            for(point in it!!){

                val latLng = LatLng(point.lat, point.lng)
                val title = point.title
                val  zoom = 4.0f
                map?.addMarker(MarkerOptions().position(latLng).title(title))

                val last = it.last()
                if(point == last ){
                    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
                }
            }



            Toast.makeText(this, "map ready", Toast.LENGTH_SHORT).show()

        })

        userCoordinatLD.observe(this, Observer{

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