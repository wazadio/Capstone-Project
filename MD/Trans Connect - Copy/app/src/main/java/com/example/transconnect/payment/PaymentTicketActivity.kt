package com.example.transconnect.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.transconnect.HomeActivity
import com.example.transconnect.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_payment_ticket.*

class PaymentTicketActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_ticket)
        btn_finalpay.isAllCaps=false

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if(user != null){

        }



        btn_finalpay.setOnClickListener {
            var id : Int = radio_group.checkedRadioButtonId
            if(id != -1){
                val radio:RadioButton = findViewById(id)
                Intent(this@PaymentTicketActivity, HomeActivity::class.java).also { intent ->
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(this,"Pembayaran dengan " + "${radio.text} berhasil",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"On button click" +
                        " nothing selected", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun radio_button_click(view: View){
        val radio : RadioButton = findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(this,"Pembayaran dengan ${radio.text}",Toast.LENGTH_SHORT).show()
    }
}