package com.kylix.demosubmissionbfaa.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.Repository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository(application)

    fun searchUser(query: String) = repository.searchUser(query)
}