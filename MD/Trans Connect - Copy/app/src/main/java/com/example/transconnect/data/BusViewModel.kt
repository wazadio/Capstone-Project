package com.example.transconnect.data

import androidx.lifecycle.ViewModel

class BusViewModel : ViewModel() {

    fun getBus(): List<Bus> = DataDummy.getdataBus()
}