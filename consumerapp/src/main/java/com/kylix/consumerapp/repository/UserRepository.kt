package com.kylix.consumerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.kylix.consumerapp.datasource.UserDataSource
import com.kylix.consumerapp.model.GithubUser
import kotlinx.coroutines.Dispatchers

class UserRepository(private val source: UserDataSource) {

    fun getUserList():LiveData<List<GithubUser>> = liveData(Dispatchers.IO){
        emit(source.getUsers())
    }
}