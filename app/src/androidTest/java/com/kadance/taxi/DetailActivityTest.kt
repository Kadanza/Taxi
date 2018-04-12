package com.kadance.taxi

import android.content.Intent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kadance.taxi.app.TaxiApp
import com.kadance.taxi.app.di.DaggerAppComponent
import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.common.view.activity.BaseActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Kenza on 12.04.2018.
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(BaseActivity::class.java)


    @Before
    fun before() {
        val app =  (mActivityRule.activity.application as TaxiApp)
        val module  = FakeAppModule(mActivityRule.activity)
        val appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent.inject(app)
        TaxiApp.appComponent = appComponent


        val intent = Intent(mActivityRule.activity, DetailActivity::class.java)
        mActivityRule.launchActivity(intent)

    }


    @Test
    fun openDatails() {

    }


    @After
    fun after() {

        Thread.sleep(60000)

    }


}