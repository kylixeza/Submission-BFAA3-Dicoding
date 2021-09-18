package com.kylix.demosubmissionbfaa.ui.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.Resource
import com.kylix.demosubmissionbfaa.data.remote.ApiService
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.util.ViewStateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {

    private val retrofit = RetrofitService.create()
    private val listUser = MutableLiveData<Resource<List<User>>>()
    
    fun getUserFollowers(username: String): LiveData<Resource<List<User>>> {
        listUser.postValue(Resource.Loading())
        retrofit.getUserFollowers(username).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val list = response.body()
                if (list.isNullOrEmpty())
                    listUser.postValue(Resource.Error(null))
                else
                    listUser.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }
        })

        return listUser
    }
}