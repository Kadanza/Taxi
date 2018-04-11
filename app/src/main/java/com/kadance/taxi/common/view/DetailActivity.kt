package com.kadance.taxi.common.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.google.android.gms.maps.SupportMapFragment
import com.kadance.taxi.R
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.presenter.MapPresenter
import com.kadance.taxi.common.view.activity.BaseActivity
import kotlinx.android.synthetic.main.act_map.*
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
class DetailActivity : BaseActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    lateinit var presenter: DetailPresenter


    // @Inject
    //lateinit var mainContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_detail)

        presenter = ViewModelProviders.of(this, modelFactory)[DetailPresenter::class.java]

       // setSupportActionBar(appbar as Toolbar?)
        //title = getString(R.string.app_name)




//        RxView.clicks(fab).subscribe({
//            presenter?.clickFab()
//        })
    }

}