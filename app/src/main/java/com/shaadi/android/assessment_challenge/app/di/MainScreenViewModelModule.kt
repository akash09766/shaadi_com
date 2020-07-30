package com.shaadi.android.assessment_challenge.app.di

import androidx.lifecycle.ViewModel
import com.shaadi.android.assessment_challenge.app.viewModel.MainActivityViewModel
import com.shaadi.android.assessment_challenge.core.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainScreenViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(searchVehicleViewModel: MainActivityViewModel): ViewModel
}