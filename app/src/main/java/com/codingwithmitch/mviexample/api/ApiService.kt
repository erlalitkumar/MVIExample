package com.codingwithmitch.mviexample.api

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.util.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>

    @GET("placeholder/user")
    fun getUser(): LiveData<GenericApiResponse<User>>
}





