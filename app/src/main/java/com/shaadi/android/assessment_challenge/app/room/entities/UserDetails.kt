package com.shaadi.android.assessment_challenge.app.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails.UserDetails.tableName
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails.UserDetails.Column
import com.shaadi.android.assessment_challenge.app.utils.MConstants

@Entity(tableName = tableName)
data class UserDetails(
    /**
     * Primary key for UserDetails table.
     */

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Column.user_id)
    val user_id: String,

    @ColumnInfo(name = Column.user_first_name)
    val user_first_name: String,
    @ColumnInfo(name = Column.user_last_name)
    val user_last_name: String,
    @ColumnInfo(name = Column.user_age)
    val user_age: Int = 0,
    @ColumnInfo(name = Column.user_location)
    val user_location: String,
    @ColumnInfo(name = Column.user_profile_large)
    val user_profile_large: String,
    @ColumnInfo(name = Column.user_profile_medium)
    val user_profile_medium: String,
    @ColumnInfo(name = Column.user_profile_thumbnail)
    val user_profile_thumbnail: String,
    @ColumnInfo(name = Column.acceptance_status)
    val acceptance_status: Int = MConstants.USER_ACCEPTANCE_STATUS_YET_TO_TAKE_DECISION

    ) {
    object UserDetails {
        const val tableName = "user_details"

        object Column {
            const val user_id = "user_id"
            const val user_first_name = "user_first_name"
            const val user_last_name = "user_last_name"
            const val user_age = "user_age"
            const val user_location = "user_location"
            const val user_profile_large = "user_profile_large"
            const val user_profile_medium = "user_profile_medium"
            const val user_profile_thumbnail = "user_profile_thumbnail"

            const val acceptance_status = "acceptance_status"
        }
    }
}
