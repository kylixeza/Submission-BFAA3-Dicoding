package com.kylix.submissionbfaa3.networking

import androidx.lifecycle.liveData
import com.kylix.submissionbfaa3.database.UserDao
import com.kylix.submissionbfaa3.utils.Resource
import kotlinx.coroutines.Dispatchers

object UserRepositories {

    fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val userSearch = RetrofitConfig.apiClient.searchUsers(query)
            emit(Resource.success(userSearch.items))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun getFollowers(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(RetrofitConfig.apiClient.userFollower(username)))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun getFollowing(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(RetrofitConfig.apiClient.userFollowing(username)))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun  getFavorite(userDao: UserDao) = userDao.getUserList()
}