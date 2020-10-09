package com.kylix.submissionbfaa3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kylix.submissionbfaa3.database.UserDao
import com.kylix.submissionbfaa3.database.UserDatabase
import com.kylix.submissionbfaa3.model.GithubUser
import com.kylix.submissionbfaa3.networking.UserFavoriteRepositories
import com.kylix.submissionbfaa3.utils.Resource
import kotlinx.coroutines.launch

class DetailViewModel(app: Application) : AndroidViewModel(app) {

    private var userDao: UserDao = UserDatabase.getDatabase(app).userDao()
    private var userFavoriteRepos: UserFavoriteRepositories

    init {
        userFavoriteRepos = UserFavoriteRepositories(userDao)
    }

    fun data(username: String): LiveData<Resource<GithubUser>> = userFavoriteRepos.getDetailUser(username)

    fun addFavorite(githubUser: GithubUser) = viewModelScope.launch {
        userFavoriteRepos.insert(githubUser)
    }

    fun removeFavorite(githubUser: GithubUser) = viewModelScope.launch {
        userFavoriteRepos.delete(githubUser)
    }

    val isFavorite: LiveData<Boolean> = userFavoriteRepos.isFavorite
}