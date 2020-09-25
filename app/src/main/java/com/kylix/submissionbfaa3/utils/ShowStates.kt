package com.kylix.submissionbfaa3.utils

import android.view.View
import com.kylix.submissionbfaa3.databinding.FavoriteFragmentBinding
import com.kylix.submissionbfaa3.databinding.FollowFragmentBinding
import com.kylix.submissionbfaa3.databinding.HomeFragmentBinding

interface ShowStates {

    fun homeLoading(homeBinding: HomeFragmentBinding): Int? = null
    fun homeSuccess(homeBinding: HomeFragmentBinding): Int? = null
    fun homeError(homeBinding: HomeFragmentBinding, message: String?): Int? = null

    fun followLoading(followBinding: FollowFragmentBinding): Int? = null
    fun followSuccess(followBinding: FollowFragmentBinding): Int? = null
    fun followError(followBinding: FollowFragmentBinding, message: String?): Int? = null

    fun favoriteLoading(favoriteFragmentBinding: FavoriteFragmentBinding): Int? = null
    fun favoriteSuccess(favoriteFragmentBinding: FavoriteFragmentBinding): Int? = null
    fun favoriteError(favoriteFragmentBinding: FavoriteFragmentBinding, message: String?): Int? = null

    fun gone() = View.GONE
    fun visible() = View.VISIBLE
}