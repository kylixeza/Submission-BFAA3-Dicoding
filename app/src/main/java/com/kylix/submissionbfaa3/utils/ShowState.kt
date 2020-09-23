package com.kylix.submissionbfaa3.utils

import com.kylix.submissionbfaa3.fragments.FollowFragment.Companion.STATE_FOLLOW
import android.content.res.Resources
import android.view.View
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.databinding.FavoriteFragmentBinding
import com.kylix.submissionbfaa3.databinding.FollowFragmentBinding
import com.kylix.submissionbfaa3.databinding.HomeFragmentBinding
import com.kylix.submissionbfaa3.fragments.FavoriteFragment.Companion.STATE_FAVORITE
import com.kylix.submissionbfaa3.fragments.HomeFragment.Companion.STATE_HOME

class ShowState(private val stateId: Int,
                private val homeBinding: HomeFragmentBinding?,
                private val followBinding: FollowFragmentBinding?,
                private val favoriteBinding: FavoriteFragmentBinding?) {


    fun loading(){
        when(stateId){
            STATE_HOME -> {
                homeBinding?.apply {
                    errLayout.mainNotFound.visibility = gone()
                    progress.start()
                    progress.loadingColor = R.color.colorAccent
                    recyclerHome.visibility = gone()
                }
            }
            STATE_FOLLOW -> {
                followBinding?.apply {
                    errLayout.mainNotFound.visibility = gone()
                    progress.start()
                    progress.loadingColor = R.color.colorAccent
                    recylerFollow.visibility = gone()
                }
            }
            STATE_FAVORITE -> {
                favoriteBinding?.apply {
                    errlayout.mainNotFound.visibility = gone()
                    progress.start()
                    progress.loadingColor = R.color.colorAccent
                    recyclerFav.visibility = gone()
                }
            }
        }
    }

    fun success(){
        when(stateId){
            STATE_HOME -> {
                homeBinding?.apply {
                    errLayout.mainNotFound.visibility = gone()
                    progress.stop()
                    recyclerHome.visibility = visible()
                }
            }
            STATE_FOLLOW -> {
                followBinding?.apply {
                    errLayout.mainNotFound.visibility = gone()
                    progress.stop()
                    recylerFollow.visibility = visible()
                }
            }
            STATE_FAVORITE -> {
                favoriteBinding?.apply {
                    errlayout.mainNotFound.visibility = gone()
                    progress.stop()
                    recyclerFav.visibility = visible()
                }
            }
        }
    }

    fun error(message: String?, resources: Resources){
        when(stateId){
            STATE_HOME -> {
                homeBinding?.apply {
                    errLayout.apply {
                        mainNotFound.visibility = visible()
                        emptyText.text = message ?: resources.getString(R.string.not_found)
                    }
                    progress.stop()
                    recyclerHome.visibility = gone()
                }
            }
            STATE_FOLLOW -> {
                followBinding?.apply {
                    errLayout.apply {
                        mainNotFound.visibility = visible()
                        emptyText.text = message ?: resources.getString(R.string.not_found)
                    }
                    progress.stop()
                    recylerFollow.visibility = gone()
                }
            }
            STATE_FAVORITE -> {
                favoriteBinding?.apply {
                    errlayout.apply {
                        mainNotFound.visibility = visible()
                        emptyText.text = message ?: resources.getString(R.string.not_found)
                    }
                    progress.stop()
                    recyclerFav.visibility = gone()
                }
            }
        }
    }

    private fun visible() = View.VISIBLE
    private fun gone() = View.GONE
}