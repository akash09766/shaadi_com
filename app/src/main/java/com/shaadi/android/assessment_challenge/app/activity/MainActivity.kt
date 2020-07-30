package com.shaadi.android.assessment_challenge.app.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.android.assessment_challenge.app.utils.showLongSnackBar
import com.shaadi.android.assessment_challenge.app.viewModel.MainActivityViewModel
import com.shaadi.android.assessment_challenge.core.ui.ViewState
import com.shaadi.android.assessment_challenge.core.ui.base.BaseActivity
import com.shaadi.android.assessment_challenge.core.utils.getViewModel
import com.shaadi.android.assessment_challenge.core.utils.observeNotNull
import com.shaadi.android.assessment_challenge.core.utils.setVisibilityWithGONE
import com.shaadi.android.assessment_challenge.core.utils.viewBinding
import com.shaadi.android.assessment_challenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by lazy { getViewModel<MainActivityViewModel>() }
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var scrollListener: RecyclerView.OnScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        getData()
        setObservers()
    }

    private fun getData() {
        viewModel.getUserListingDataFromDB()
        viewModel.getUserListingData()
    }

    private fun setObservers() {
        viewModel._userListingResponse.observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {

                    state.data.results?.forEach {
                        Log.d(TAG, "setObservers: _userListingResponse Name : ${it.name.first.plus("  ${it.name.last}")}")
                    }

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
                        Log.d(TAG, "setObservers: _userDetailsList Name : ${it?.user_first_name.plus("  ${it?.user_last_name}")}")
                    }

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
    }
}