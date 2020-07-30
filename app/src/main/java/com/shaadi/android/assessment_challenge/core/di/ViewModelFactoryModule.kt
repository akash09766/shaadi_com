package com.shaadi.android.assessment_challenge.core.di

import androidx.lifecycle.ViewModelProvider
import com.shaadi.android.assessment_challenge.core.di.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}