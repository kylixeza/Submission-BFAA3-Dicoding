package com.kylix.submissionbfaa3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kylix.submissionbfaa3.database.UserDatabase
import com.kylix.submissionbfaa3.model.GithubUser
import com.kylix.submissionbfaa3.networking.UserRepositories

class FavoriteViewModel(app: Application) : AndroidViewModel(app) {
    val dataFavorite: LiveData<List<GithubUser>>

    init {
        val userDao = UserDatabase.getDatabase(app).userDao()
        dataFavorite = UserRepositories.getFavorite(userDao)
    }
}