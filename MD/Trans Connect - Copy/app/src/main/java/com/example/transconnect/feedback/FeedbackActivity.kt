package com.example.transconnect.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.transconnect.HomeActivity
import com.example.transconnect.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var koridord : EditText
    private lateinit var feedbackd : EditText
    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        koridord = findViewById(R.id.koridoric)
        feedbackd = findViewById(R.id.feedbackic)
        btnSave = findViewById(R.id.btn_feedback)

        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val koridord = koridoric.text.toString().trim()
        val feedbackd = feedbackic.text.toString().trim()

        if (koridord.isEmpty()) {
            koridoric.error = "Koridor harus diisi"
            koridoric.requestFocus()
            return
        }

        if (feedbackd.isEmpty()) {
            feedbackic.error = " Feedback harus diisi"
            feedbackic.requestFocus()
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("feedback")

        val feedId = ref.push().key

        val feed = Feedback(feedId, koridord, feedbackd)

        if (feedId != null) {
            ref.child(feedId).setValue(feed).addOnCompleteListener {
                Intent(this@FeedbackActivity, HomeActivity::class.java).also { intent ->
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}