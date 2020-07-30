package com.shaadi.android.assessment_challenge.app.listeners

import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails

interface UserItemClickListener {
    fun onItemClick(userDetails: UserDetails,isAccepted: Boolean)
}