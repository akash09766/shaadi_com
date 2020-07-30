package com.shaadi.android.assessment_challenge.app.room.dao

import androidx.room.*
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Transaction
    @Query("SELECT * FROM user_details")
    fun getUserDetails(): Flow<List<UserDetails?>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(CountryDetails: List<UserDetails?>?)

    // update like dislike status
    @Query("UPDATE user_details SET acceptance_status = :acceptance_status WHERE user_id = :user_id")
    fun updateAcceptanceStatus(acceptance_status: Int, user_id: String?): Int

    @Query("DELETE FROM user_details")
    fun nukeUserDetailsTable()
}