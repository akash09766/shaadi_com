package com.shaadi.android.assessment_challenge.app.room.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shaadi.android.assessment_challenge.app.room.dao.UserDetailsDao
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails

@Database(
    entities = [UserDetails::class],
    version = ShaadiAssessmentDatabaseMigrations.latestVersion
)
abstract class ShaadiAssessMentChallengeAppDatabase : RoomDatabase() {

    /**
     * Get user details
     */
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {

        private const val databaseName = "shaadi_com-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, ShaadiAssessMentChallengeAppDatabase::class.java, databaseName)
                .addMigrations(*ShaadiAssessmentDatabaseMigrations.allMigrations)
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, ShaadiAssessMentChallengeAppDatabase::class.java)
                .build()
    }
}