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
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import org.junit.rules.TestRule




/**
 * Created by Kenza on 12.04.2018.
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {


    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun beforeActivityLaunched() {

            val app = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TaxiApp)
            val module = FakeAppModule(app)
            val appComponent = DaggerAppComponent.builder().appModule(module).build()!!
            app.setComponent(appComponent)

            super.beforeActivityLaunched()
        }
    }


    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()



    @Before
    fun before() {

    }




    @Test
    fun openDatails() {

//        async {
//            delay(3000)
            mActivityRule.activity.presenter.createPointByAddress("Moskva")

//        }



        //onView(ViewMatchers.withId(R.id.fab)).perform(click())


    }


    @After
    fun after() {

        Thread.sleep(600000)

    }


}