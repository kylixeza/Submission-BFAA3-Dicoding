package com.kylix.demosubmissionbfaa.data.remote

import com.kylix.demosubmissionbfaa.model.SearchResponse
import com.kylix.demosubmissionbfaa.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //endpoint: BASE_URL + Value GET (https://api.github.com/search/users)
    @GET("search/users")
    fun searchUsers (
        @Query("q")
        query: String
    ): Call<SearchResponse>

    //endpoint: BASE_URL + Value GET (https://api.github.com/users/{username})
    @GET("users/{username}")
    fun getDetailUser (
        @Path("username")
        username: String
    ): Call<User>

    @GET("users/{username}/followers")
    fun getUserFollowers (
        @Path("username")
        username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    fun getUserFollowing (
        @Path("username")
        username: String
    ): Call<List<User>>
}