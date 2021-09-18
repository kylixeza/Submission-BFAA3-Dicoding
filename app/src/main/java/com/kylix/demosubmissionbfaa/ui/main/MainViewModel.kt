package com.kylix.demosubmissionbfaa.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.Resource
import com.kylix.demosubmissionbfaa.data.remote.ApiService
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.model.SearchResponse
import com.kylix.demosubmissionbfaa.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val retrofit: ApiService = RetrofitService.create()
    private val listUser = MutableLiveData<Resource<List<User>>>()

    fun searchUser(query: String): LiveData<Resource<List<User>>> {
        listUser.postValue(Resource.Loading())
        retrofit.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val list = response.body()?.items
                if (list.isNullOrEmpty())
                    listUser.postValue(Resource.Error(null))
                 else
                    listUser.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }

        })
        return listUser
    }
}