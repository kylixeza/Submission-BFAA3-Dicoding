package com.kylix.demosubmissionbfaa.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.demosubmissionbfaa.data.Repository
import com.kylix.demosubmissionbfaa.model.User
import kotlinx.coroutines.launch


class DetailViewModel(application: Application): AndroidViewModel(application) {

    val repository = Repository(application)

    suspend fun getDetailUser(username: String) = repository.getDetailUser(username)

    fun insertFavoriteUser(user: User) = viewModelScope.launch {
        repository.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: User) = viewModelScope.launch {
        repository.deleteFavoriteUser(user)
    }
}