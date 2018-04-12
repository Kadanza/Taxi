package com.kadance.taxi


import android.app.Application
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.arranlomas.daggerviewmodelhelper.AppInjector
import com.kadance.taxi.app.TaxiApp
import com.kadance.taxi.app.d
import com.kadance.taxi.app.di.DaggerAppComponent
import com.kadance.taxi.app.di.module.AppModule
import com.kadance.taxi.app.e
import com.kadance.taxi.common.view.MapActivity
import com.kadance.taxi.common.view.activity.BaseActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MapActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(BaseActivity::class.java)





    @Before
    fun before() {
        val app =  (mActivityRule.activity.application as TaxiApp)

//        AppInjector.init(app)
        val module  = FakeAppModule(mActivityRule.activity)
        val appComponent = DaggerAppComponent.builder().appModule(module).build()!!
        appComponent.inject(app)
        TaxiApp.appComponent = appComponent


        val intent = Intent(mActivityRule.activity, MapActivity::class.java)
        mActivityRule.launchActivity(intent)

    }


    @Test
    fun openDatails() {

    }


    @Test
    fun mapActivityTest() {
      //  val actionMenuItemView = onView(allOf(withId(R.id.action_detail), childAtPosition(childAtPosition(withId(R.id.toolbar), 1), 0), isDisplayed()))
       // actionMenuItemView.perform(click())

        val x =mActivityRule.activity.d( "msg 1")
        val x2 =mActivityRule.activity.e("msg 1")

        d("qwertuy")

    }


    @After
    fun after() {

        Thread.sleep(4000000)

    }



    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(position)
            }
        }
    }
}
