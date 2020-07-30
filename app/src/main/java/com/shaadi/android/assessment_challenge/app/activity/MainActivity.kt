package com.shaadi.android.assessment_challenge.app.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.android.assessment_challenge.R
import com.shaadi.android.assessment_challenge.app.adapter.UserListAdapter
import com.shaadi.android.assessment_challenge.app.listeners.UserItemClickListener
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails
import com.shaadi.android.assessment_challenge.app.utils.*
import com.shaadi.android.assessment_challenge.app.viewModel.MainActivityViewModel
import com.shaadi.android.assessment_challenge.core.ui.ViewState
import com.shaadi.android.assessment_challenge.core.ui.base.BaseActivity
import com.shaadi.android.assessment_challenge.core.utils.getViewModel
import com.shaadi.android.assessment_challenge.core.utils.observeNotNull
import com.shaadi.android.assessment_challenge.core.utils.setVisibilityWithGONE
import com.shaadi.android.assessment_challenge.core.utils.viewBinding
import com.shaadi.android.assessment_challenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity(),UserItemClickListener {

    private val TAG = "MainActivity"

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by lazy { getViewModel<MainActivityViewModel>() }

    private val userListAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initView()
        getData()
        setObservers()
    }

    private fun getData() {
        viewModel.getUserListingDataFromDB()
        viewModel.getUserListingData()
    }

    private fun initView() {
        if (resources.isInLandScapeMode()) {
            binding.userRecyclerView.addItemDecoration(
                SpacesItemDecoration(
                    30
                )
            )
            binding.userRecyclerView.apply {
                layoutManager = GridLayoutManager(this.context, 2)
                adapter = userListAdapter
            }
        } else {

            binding.userRecyclerView.addItemDecoration(
                LineaLayoutItemDecoration(
                    resources.getDimension(R.dimen.user_list_row_margin).toInt()
                )
            )

            binding.userRecyclerView.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = userListAdapter
            }
        }

        userListAdapter.setItemClickListener(this)
    }


    private fun setObservers() {
        viewModel._userListingResponse.observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {

                  /*  state.data.results?.forEach {
                        Log.d(
                            TAG,
                            "setObservers: _userListingResponse Name : ${it.name.first.plus("  ${it.name.last}")}"
                        )
                    }*/
                }
                is ViewState.Loading -> {
                    Log.d(TAG, "setObservers: _userListingResponse Loading : ${state.data}")
                    binding.progressCircular.setVisibilityWithGONE(state.data)
                }

                is ViewState.Error -> {
                    Log.e(TAG, state.message)
                    showLongSnackBar(binding.root, state.message)
                }
            }
        }

        viewModel._userDetailsList.observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    state.data?.forEach {
                        Log.d(
                            TAG,
                            "setObservers: _userDetailsList Name : ${it?.user_first_name.plus("  ${it?.user_last_name} and acceptance_status : ${it?.acceptance_status}")}"
                        )
                    }
                    userListAdapter.setData(state.data)
                }
                is ViewState.Loading -> {
                    Log.d(TAG, "setObservers: _userDetailsList Loading : ${state.data}")
                    binding.progressCircular.setVisibilityWithGONE(state.data)
                }

                is ViewState.Error -> {
                    Log.e(TAG, state.message)
                    showLongSnackBar(binding.root, state.message)
                }
            }
        }

        viewModel._updateUserAcceptance.observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {

                    Log.d(TAG, "setObservers: _updateUserAcceptance user acceptance status : ${state.data}")
                }
                is ViewState.Loading -> {
                    Log.d(TAG, "setObservers: _updateUserAcceptance Loading : ${state.data}")
                }

                is ViewState.Error -> {
                    Log.e(TAG, state.message)
                    showLongSnackBar(binding.root, state.message)
                }
            }
        }
    }

    override fun onItemClick(userDetails: UserDetails,isAccepted: Boolean) {
        Log.d(TAG, "onItemClick() called with: userDetails = ${userDetails.user_first_name} and isAccepted : $isAccepted")
        viewModel.updateUserAcceptanceDetail(user_id = userDetails.user_id,
            acceptance_status = if(isAccepted) MConstants.USER_ACCEPTANCE_STATUS_ACCEPTED else MConstants.USER_ACCEPTANCE_STATUS_REJECTED
        )
    }
}