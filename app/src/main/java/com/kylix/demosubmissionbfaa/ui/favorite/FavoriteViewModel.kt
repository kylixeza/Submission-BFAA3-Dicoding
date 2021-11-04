package com.kylix.demosubmissionbfaa.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kylix.demosubmissionbfaa.data.Repository
import com.kylix.demosubmissionbfaa.data.Resource
import com.kylix.demosubmissionbfaa.model.User
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository(application)

    suspend fun getFavoriteList() = repository.getFavoriteList()
}