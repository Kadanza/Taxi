package com.kadance.taxi

import android.content.Intent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kadance.taxi.app.TaxiApp
import com.kadance.taxi.app.di.DaggerAppComponent
import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.common.view.activity.BaseActivity
import com.kadance.taxi.net.GoogleServicerApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder

/**
 * Created by Kenza on 12.04.2018.
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(BaseActivity::class.java)

    @Rule
    @JvmField
    var mActivityRule2 = ActivityTestRule(DetailActivity::class.java)


    @Before
    fun before() {
        val app = (mActivityRule.activity.application as TaxiApp)
        val module = FakeAppModule(mActivityRule.activity)
        val appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent.inject(app)
        TaxiApp.appComponent = appComponent


        val intent = Intent(mActivityRule.activity, DetailActivity::class.java)
//        mActivityRule.launchActivity(intent)

        mActivityRule.launchActivity(intent)

    }


    @Test
    fun openDatails() {


        val x2 = mActivityRule2.activity


        val server = GoogleServicerApi.create()
        val addr = URLEncoder.encode("Rostov on don", "UTF-8")



//        y?.createPointByAddress(addr)
        x2.presenter.createPointByAddress("qwe")

        print(x2.toString())


        //onView(ViewMatchers.withId(R.id.fab)).perform(click())


    }


    @After
    fun after() {

        Thread.sleep(600000)

    }


}