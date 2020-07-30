package com.shaadi.android.assessment_challenge.app.api

import com.shaadi.android.assessment_challenge.app.model.globalData.userListting.UserListingResponse
import com.shaadi.android.assessment_challenge.app.utils.SsConfig
import retrofit2.http.GET

/**
 * List of API Calls.
 */
interface ApiService {

    /**
     * get user listing data .
     */

    @GET(SsConfig.USER_LISTING)
    suspend fun getUserListingData(): UserListingResponse
}
