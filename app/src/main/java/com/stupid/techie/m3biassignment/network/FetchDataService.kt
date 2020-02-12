package com.stupid.techie.m3biassignment.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface FetchDataService {

    @GET("/jokes/random/")
    fun getJoke(): Call<ResponseBody>

}