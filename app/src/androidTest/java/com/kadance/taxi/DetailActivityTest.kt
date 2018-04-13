package com.kadance.taxi

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kadance.taxi.app.TaxiApp
import com.kadance.taxi.app.di.AppComponent
import com.kadance.taxi.app.di.DaggerAppComponent
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.common.view.activity.BaseActivity
import com.kadance.taxi.net.GoogleServicerApi
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder
import android.app.Activity
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.repo.NetRepo
import dagger.android.AndroidInjector
import org.mockito.Mockito.mock
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule




/**
 * Created by Kenza on 12.04.2018.
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(DetailActivity::class.java , false )

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

//    @Rule
//    @JvmField
//    var mActivityRule2 = ActivityTestRule(DetailActivity::class.java, false, true )


    private var act3: DetailActivity? = null

    @Before
    fun before() {
        val app = (mActivityRule.activity.application as TaxiApp)
        val module = FakeAppModule(mActivityRule.activity)
        val appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent.inject(app)
        TaxiApp.appComponent = appComponent



//        val intent = Intent(mActivityRule.activity, DetailActivity::class.java)
        act3 = mActivityRule.launchActivity( Intent())

        //mActivityRule.launchActivity(intent)



    }




    @Test
    fun openDatails() {

       // val y2  =   InstrumentationRegistry.getContext() as DetailActivity



        mActivityRule.activity.presenter.createPointByAddress("Rostov on don")





        //onView(ViewMatchers.withId(R.id.fab)).perform(click())


    }


    @After
    fun after() {

        Thread.sleep(600000)

    }


}