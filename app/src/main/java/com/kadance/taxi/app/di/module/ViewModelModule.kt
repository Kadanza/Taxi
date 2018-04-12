package com.kadance.taxi.app.di.module

import dagger.Module
import android.arch.lifecycle.ViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import android.arch.lifecycle.ViewModelProvider
import com.arranlomas.daggerviewmodelhelper.ViewModelFactory
import com.kadance.taxi.app.di.ViewModelKey
import com.kadance.taxi.common.presenter.DetailPresenter
import com.kadance.taxi.common.presenter.MapPresenter

@Module
abstract class ViewModelModule {

    // PROVIDE YOUR OWN MODELS HERE
    @Binds
    @IntoMap
    @ViewModelKey(MapPresenter::class)
    internal abstract fun bindMapPresenterViewModel(presenter: MapPresenter): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailPresenter::class)
    internal abstract fun bindDetailPresenterViewModel(presenter: DetailPresenter): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory




}