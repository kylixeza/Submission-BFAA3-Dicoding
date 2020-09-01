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
                homeBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                homeBinding?.progress?.visibility = View.VISIBLE
                homeBinding?.recyclerHome?.visibility = View.GONE
            }
            STATE_FOLLOW -> {
                followBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                followBinding?.progress?.visibility = View.VISIBLE
                followBinding?.recylerFollow?.visibility = View.GONE
            }
            STATE_FAVORITE -> {
                favoriteBinding?.errlayout?.mainNotFound?.visibility = View.GONE
                favoriteBinding?.progress?.visibility = View.VISIBLE
                favoriteBinding?.recyclerFav?.visibility = View.GONE
            }
        }
    }

    fun success(){
        when(stateId){
            STATE_HOME -> {
                homeBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                homeBinding?.progress?.visibility = View.GONE
                homeBinding?.recyclerHome?.visibility = View.VISIBLE
            }
            STATE_FOLLOW -> {
                followBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                followBinding?.progress?.visibility = View.GONE
                followBinding?.recylerFollow?.visibility = View.VISIBLE
            }
            STATE_FAVORITE -> {
                favoriteBinding?.errlayout?.mainNotFound?.visibility = View.GONE
                favoriteBinding?.progress?.visibility = View.GONE
                favoriteBinding?.recyclerFav?.visibility = View.VISIBLE
            }
        }
    }

    fun error(message: String?, resources: Resources){
        when(stateId){
            STATE_HOME -> {
                homeBinding?.errLayout?.mainNotFound?.visibility = View.VISIBLE
                homeBinding?.errLayout?.emptyText?.text = message ?: resources.getString(R.string.not_found)
                homeBinding?.progress?.visibility = View.GONE
                homeBinding?.recyclerHome?.visibility = View.GONE
            }
            STATE_FOLLOW -> {
                followBinding?.errLayout?.mainNotFound?.visibility = View.VISIBLE
                followBinding?.errLayout?.emptyText?.text = message ?: resources.getString(R.string.not_found)
                followBinding?.progress?.visibility = View.GONE
                followBinding?.recylerFollow?.visibility = View.GONE
            }
            STATE_FAVORITE -> {
                favoriteBinding?.errlayout?.mainNotFound?.visibility = View.VISIBLE
                favoriteBinding?.errlayout?.emptyText?.text = message?: resources.getString(R.string.not_found)
                favoriteBinding?.progress?.visibility = View.GONE
                favoriteBinding?.recyclerFav?.visibility = View.GONE
            }
        }
    }
}