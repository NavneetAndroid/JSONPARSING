package com.example.jsonparsing

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {

    @GET("posts")


    fun getData():Call<List<MyDataItem>>
}