package com.shaadi.android.assessment_challenge.app.room.db


import androidx.room.migration.Migration

/**
 * Describes migration related to [ShaadiAssessmentDatabaseMigrations].
 */
internal object ShaadiAssessmentDatabaseMigrations {

    // Bump this on changing the schema
    const val latestVersion = 1

    val allMigrations: Array<Migration>
        get() = arrayOf(
            //// Add migrations here
        )
}