package com.shaadi.android.assessment_challenge.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.android.assessment_challenge.R
import com.shaadi.android.assessment_challenge.app.listeners.UserItemClickListener
import com.shaadi.android.assessment_challenge.app.room.entities.UserDetails
import com.shaadi.android.assessment_challenge.app.utils.MConstants
import com.shaadi.android.assessment_challenge.app.utils.loadImage
import com.shaadi.android.assessment_challenge.core.utils.invisible
import com.shaadi.android.assessment_challenge.core.utils.visible

class UserListAdapter :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    private val TAG = UserListAdapter::class.java.simpleName

    private val userList = ArrayList<UserDetails?>()
    private lateinit var listener: UserItemClickListener

    fun setData(userList: List<UserDetails?>) {
        this.userList.clear()
        userList?.let { this.userList.addAll(it) }
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: UserItemClickListener) {
        this.listener = listener
    }

    class UserListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val userName = itemView.findViewById(R.id.user_name) as TextView
        val userAge = itemView.findViewById(R.id.user_age) as TextView
        val userLocation = itemView.findViewById(R.id.user_location) as TextView
        val acceptTvTitle = itemView.findViewById(R.id.accept_tv) as TextView
        val rejectTvTitle = itemView.findViewById(R.id.reject_tv) as TextView

        val acceptIv = itemView.findViewById(R.id.accept_iv) as ImageView
        val rejectIv = itemView.findViewById(R.id.reject_iv) as ImageView

        val userProfilePic = itemView.findViewById(R.id.user_iv) as ImageView

        val userActionGroup = itemView.findViewById(R.id.user_actions_group) as Group
        val acceptanceStatusTv = itemView.findViewById(R.id.acceptance_status_tv) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_list_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = userList[position]

        if (item?.acceptance_status == MConstants.USER_ACCEPTANCE_STATUS_YET_TO_TAKE_DECISION) {
            holder.userActionGroup.visible()
            holder.acceptanceStatusTv.invisible()
        } else {
            holder.userActionGroup.invisible()
            holder.acceptanceStatusTv.visible()
            if (item?.acceptance_status == MConstants.USER_ACCEPTANCE_STATUS_ACCEPTED) {
                holder.acceptanceStatusTv.setTextColor(
                    ContextCompat.getColor(
                        holder.acceptanceStatusTv.context,
                        R.color.colorAccent
                    )
                )
                holder.acceptanceStatusTv.background = ContextCompat.getDrawable(
                    holder.acceptanceStatusTv.context,
                    R.drawable.user_acceptance_tv_accepted_bg
                )

                holder.acceptanceStatusTv.text =
                    holder.acceptanceStatusTv.context.getString(R.string.user_acceptance_status_accepted)
            } else if (item?.acceptance_status == MConstants.USER_ACCEPTANCE_STATUS_REJECTED) {
                holder.acceptanceStatusTv.setTextColor(
                    ContextCompat.getColor(
                        holder.acceptanceStatusTv.context,
                        android.R.color.darker_gray
                    )
                )
                holder.acceptanceStatusTv.background = ContextCompat.getDrawable(
                    holder.acceptanceStatusTv.context,
                    R.drawable.user_acceptance_tv_rejected_bg
                )

                holder.acceptanceStatusTv.text =
                    holder.acceptanceStatusTv.context.getString(R.string.user_acceptance_status_rejected)
            }
        }

        holder.userProfilePic.loadImage(item?.user_profile_large)
        Log.d(TAG, "onBindViewHolder: user_profile_large : ${item?.user_profile_large}")

        holder.userName.text =
            item?.user_first_name.plus(" ${item?.user_last_name?.substring(0, 1)}")
        holder.userAge.text = item?.user_age.toString().plus(" ${MConstants.USER_AGE_UNIT}")
        holder.userLocation.text = item?.user_location

        holder.acceptIv.setOnClickListener {
            if (item != null) {
                listener.onItemClick(item, true)
            }
        }
        holder.rejectIv.setOnClickListener {
            if (item != null) {
                listener.onItemClick(item, false)
            }
        }
    }
}