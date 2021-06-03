package com.example.transconnect.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.transconnect.R
import com.example.transconnect.data.Bus
import com.example.transconnect.data.Helper.TYPE_BUS
import com.example.transconnect.payment.PaymentTicketActivity
import com.example.transconnect.register.ResetPasswordActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var result: Bus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val viewModel = ViewModelProvider(
            this@DetailActivity,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val id = intent.getStringExtra(EXTRA_DATA)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if(type.equals(TYPE_BUS,ignoreCase = false)){
            id?.let {
                viewModel.setBus(it)
            }
            result = viewModel.getDetail(TYPE_BUS)
        }

        bus_nama.text = result.bKoridor
        nomorbustxt.text = result.bNomorbus
        availseattxt.text = result.bAvailseat
        passangertxt.text = result.bPassanger
        bus_photo.setImageResource(result.bImagesrc)

        btn_payticket.setOnClickListener {
            Intent(this@DetailActivity, PaymentTicketActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}