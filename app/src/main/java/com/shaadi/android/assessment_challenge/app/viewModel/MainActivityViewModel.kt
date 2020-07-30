package com.shaadi.android.assessment_challenge.app.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaadi.android.assessment_challenge.app.dataSource.DataRepository
import com.shaadi.android.assessment_challenge.app.model.globalData.userListting.UserListingResponse
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails
import com.shaadi.android.assessment_challenge.core.ui.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val TAG = MainActivityViewModel::class.java.simpleName
    private lateinit var job: Job

    private var userListingResponse = MutableLiveData<ViewState<UserListingResponse>>()
    val _userListingResponse: LiveData<ViewState<UserListingResponse>>
        get() = userListingResponse

    private var userDetailsList = MutableLiveData<ViewState<List<UserDetails?>>>()
    val _userDetailsList: LiveData<ViewState<List<UserDetails?>>>
        get() = userDetailsList

    fun getUserListingData() {
        Log.d(TAG, "getUserListingData: ")
        job = CoroutineScope(Dispatchers.Main).launch {
            dataRepository.getUserListingData().collect {
                userListingResponse.value = it
            }
        }
    }

    fun getUserListingDataFromDB() {
        Log.d(TAG, "getUserListingDataFromDB: ")
        job = CoroutineScope(Dispatchers.Main).launch {
            dataRepository.getUserListingFromDB().collect {
                userDetailsList.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}