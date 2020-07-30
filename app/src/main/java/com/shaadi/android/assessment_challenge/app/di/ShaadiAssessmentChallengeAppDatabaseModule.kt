package com.shaadi.android.assessment_challenge.app.di

import android.app.Application
import com.shaadi.android.assessment_challenge.app.room.dao.UserDetailsDao
import com.shaadi.android.assessment_challenge.app.room.db.ShaadiAssessMentChallengeAppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ShaadiAssessmentChallengeAppDatabaseModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): ShaadiAssessMentChallengeAppDatabase = ShaadiAssessMentChallengeAppDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDetailsDao(db: ShaadiAssessMentChallengeAppDatabase): UserDetailsDao = db.userDetailsDao()
}