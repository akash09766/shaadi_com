package com.shaadi.android.assessment_challenge.app.di

import android.app.Application
import com.shaadi.android.assessment_challenge.app.application.ShaadiAssessmentChallengeApplication
import com.shaadi.android.assessment_challenge.core.di.GoogleServiceModule
import com.shaadi.android.assessment_challenge.core.di.NetworkServiceModule
import com.shaadi.android.assessment_challenge.core.di.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Dagger support
        AndroidInjectionModule::class,
        // Global
        ShaadiAssessmentChallengeAppDatabaseModule::class,
        NetworkServiceModule::class,
        GoogleServiceModule::class,
        ViewModelFactoryModule::class,
        // feature
        FeatureBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<ShaadiAssessmentChallengeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(newsApp: ShaadiAssessmentChallengeApplication)
}
