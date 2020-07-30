package com.shaadi.android.assessment_challenge.app.dataSource

import android.util.Log
import com.shaadi.android.assessment_challenge.app.api.ApiService
import com.shaadi.android.assessment_challenge.app.api.GoogleService
import com.shaadi.android.assessment_challenge.app.errorManagement.NetworkError
import com.shaadi.android.assessment_challenge.app.errorManagement.NetworkErrorForGoogle
import com.shaadi.android.assessment_challenge.app.model.globalData.userListting.UserListingResponse
import com.shaadi.android.assessment_challenge.app.room.dao.UserDetailsDao
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails
import com.shaadi.android.assessment_challenge.app.utils.MConstants
import com.shaadi.android.assessment_challenge.app.utils.convertToDbModel
import com.shaadi.android.assessment_challenge.core.ui.ViewState
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

interface DataRepository {
    fun getGooglePing(): Flow<String>
    fun getUserListingData(): Flow<ViewState<UserListingResponse>>
    fun getUserListingFromDB(): Flow<ViewState<List<UserDetails?>>>
    fun updateUserAcceptanceDetail(acceptance_status: Int, user_id: String?): Flow<ViewState<Boolean>>
}

@Singleton
class DefaultDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val googleService: GoogleService,
    private val userDetailsDao: UserDetailsDao
    ) : DataRepository {
    val TAG = "DefaultDataRepository"


    override fun getUserListingFromDB(): Flow<ViewState<List<UserDetails?>>> {
        return flow {
            Log.d(TAG, "getUserListingFromDB()")
            emit(ViewState.loading(true))

            userDetailsDao.getUserDetails()
                .collect { UserDetailsState ->
                    Log.d(TAG, "getUserListingFromDB() collect ")

                    if (UserDetailsState != null) {
                        Log.d(TAG, "getUserListingFromDB() from DB************************  size : ${UserDetailsState.size}")
                        emit(ViewState.loading(false))
                        emit(ViewState.success(UserDetailsState))
                    }
                }
        }.catch { error ->
            emit(ViewState.loading(false))
            emit(ViewState.error(NetworkError(error).getAppErrorMessage()))
        }.flowOn(Dispatchers.IO)
    }

    override fun updateUserAcceptanceDetail(acceptance_status: Int, user_id: String?): Flow<ViewState<Boolean>> {
        Log.d(TAG, "updateUserAcceptanceDetail() called with: acceptance_status = $acceptance_status, user_id = $user_id")
        return flow {
            val updateStatus = userDetailsDao.updateAcceptanceStatus(acceptance_status = acceptance_status, user_id = user_id)
            emit(ViewState.success(updateStatus == 1))

        }.catch { error ->
            emit(ViewState.error(NetworkError(error).getAppErrorMessage()))
            Log.e(TAG, "error : ${error.message}")
        }.flowOn(Dispatchers.IO)
    }

    override fun getUserListingData(): Flow<ViewState<UserListingResponse>> {
        Log.d(TAG, "getCovidData: ")
        return flow {
            emit(ViewState.loading(true))
            getGooglePing().collect { response ->
                if (response.contentEquals(MConstants.SUCCESS)) {
                    val userListingResponse = apiService.getUserListingData()
                    userDetailsDao.insertUserDetails(userListingResponse.convertToDbModel(userListingResponse)!!)
                    emit(ViewState.loading(false))
                        emit(ViewState.success(userListingResponse))
                } else {
                    emit(ViewState.loading(false))
                    emit(ViewState.error(response))
                }
            }
        }.catch { error ->
            emit(ViewState.loading(false))
            emit(ViewState.error(NetworkError(error).getAppErrorMessage()))
        }.flowOn(Dispatchers.IO)
    }

    override fun getGooglePing(): Flow<String> = flow {
        val response = googleService.getGoogle()
        if (response.isSuccessful)
            emit(MConstants.SUCCESS)
    }.catch {
        emit(NetworkErrorForGoogle(it).getAppErrorMessage())
    }.flowOn(Dispatchers.IO)
}

@Module
interface DataRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultDataRepository): DataRepository
}