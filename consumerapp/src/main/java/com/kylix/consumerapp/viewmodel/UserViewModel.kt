package com.kylix.consumerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kylix.consumerapp.datasource.UserDataSource
import com.kylix.consumerapp.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val source = UserDataSource(application.contentResolver)
        repository = UserRepository(source)
    }

    var userLists = repository.getUserList()
}