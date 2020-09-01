package com.kylix.submissionbfaa3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kylix.submissionbfaa3.model.GithubUser
import com.kylix.submissionbfaa3.networking.UserRepositories
import com.kylix.submissionbfaa3.utils.Resource

class HomeViewModel : ViewModel() {

    private val username: MutableLiveData<String> = MutableLiveData()

    val searchResult: LiveData<Resource<List<GithubUser>>> = Transformations
        .switchMap(username) {
            UserRepositories.searchUsers(it)
        }

    fun setSearch(query: String) {
        if (username.value == query) {
            return
        }
        username.value = query
    }
}