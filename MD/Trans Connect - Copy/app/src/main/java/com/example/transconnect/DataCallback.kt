package com.example.transconnect

import com.example.transconnect.data.Bus

interface DataCallback {
    fun onItemClicked(data: Bus)
}