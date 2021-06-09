package com.example.transconnect.retrofits

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transconnect.retrofits.data.retBus
import com.example.transconnect.retrofits.data.ResponseListBus
import com.example.transconnect.retrofits.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusViewModel : ViewModel() {

    private val _listBus = MutableLiveData<List<retBus>>()
    val listRetBus : LiveData<List<retBus>> = _listBus

    fun getListBus(){
        val client = ApiConfig.getApiservice().getListBus()
        client.enqueue(object : Callback<ResponseListBus>{
            override fun onResponse(call: Call<ResponseListBus>, response: Response<ResponseListBus>) {
                _listBus.value = response.body()?.insidebuses
                Log.e(TAG,"${response.code()}")
            }

            override fun onFailure(call: Call<ResponseListBus>, t: Throwable) {
                Log.e(TAG,"${t.message}")
            }

        })
    }

}