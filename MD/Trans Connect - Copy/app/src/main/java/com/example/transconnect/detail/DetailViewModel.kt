package com.example.transconnect.detail

import androidx.lifecycle.ViewModel
import com.example.transconnect.data.Bus
import com.example.transconnect.data.DataDummy
import com.example.transconnect.data.Helper

class DetailViewModel : ViewModel(){

    private lateinit var busKoridor: String

    private fun getListBus(): ArrayList<Bus> = DataDummy.getdataBus()

    fun setBus(busKoridor: String){
        this.busKoridor = busKoridor
    }

    fun getDetail(category: String): Bus{
        lateinit var result: Bus
        when(category){
            Helper.TYPE_BUS -> {
                val listBus = getListBus()
                for(bus in listBus){
                    if(bus.bKoridor == busKoridor){
                        result = bus
                        break
                    }
                }
            }
        }
        return result
    }
}