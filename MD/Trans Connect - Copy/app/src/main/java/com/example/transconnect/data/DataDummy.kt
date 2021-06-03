package com.example.transconnect.data

import com.example.transconnect.R

object DataDummy {
    fun getdataBus(): ArrayList<Bus>{
        return arrayListOf(
                Bus("tj01","Blok M - Masjid Agung","11320", R.drawable.trans1,"3","18"),
                Bus("tj02","Masjid Agung -Bundaran Senayan","11420", R.drawable.trans2,"9","12"),
                Bus("tj03","Bundaran Senayan - Gelora Bund Karno","11310", R.drawable.trans1,"0","21"),
                Bus("tj04","Gelora Bund Karno - Polda Metro Jaya","16582", R.drawable.trans2,"6","15"),
                Bus("tj05","Polda Metro Jaya - Bendungan Hilir","12602", R.drawable.trans1,"1","20"),
                Bus("tj06","Bendungan Hilir - Karet Sudirman","15834", R.drawable.trans2,"3","18"),
        )
    }
}