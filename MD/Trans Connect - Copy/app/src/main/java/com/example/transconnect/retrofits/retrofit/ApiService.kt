package com.example.transconnect.retrofits.retrofit

import com.example.transconnect.retrofits.data.ResponseListBus
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("insidebus")
    fun getListBus(): Call<ResponseListBus>
}