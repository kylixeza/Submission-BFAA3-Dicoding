package com.kylix.demosubmissionbfaa.ui.follower

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kylix.demosubmissionbfaa.data.Repository

class FollowerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)

    fun getUserFollowers(username: String) = repository.getUserFollowers(username)
}