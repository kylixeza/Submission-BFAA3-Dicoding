package com.kylix.demosubmissionbfaa.ui.main

import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.remote.ApiService
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.model.SearchResponse
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.util.ViewStateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val retrofit: ApiService = RetrofitService.create()

    fun searchUser(query: String, viewCallback: ViewStateCallback<List<User>>) {
        viewCallback.onLoading()
        retrofit.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val list = response.body()?.items
                if (list.isNullOrEmpty())
                    viewCallback.onFailed(null)
                 else
                    viewCallback.onSuccess(list)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                viewCallback.onFailed(t.message)
            }

        })
    }
}