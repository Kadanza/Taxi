package com.kadance.taxi.app.di.module

import com.kadance.taxi.common.view.DetailActivity
import com.kadance.taxi.common.view.MapActivity
import com.kadance.taxi.common.view.activity.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun bindMapActivity(): MapActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    internal abstract fun bindBaseActivity(): BaseActivity
}