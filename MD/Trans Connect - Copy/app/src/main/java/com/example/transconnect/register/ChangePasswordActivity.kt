package com.example.transconnect.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.transconnect.HomeActivity
import com.example.transconnect.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser

        layoutPassword.visibility = View.VISIBLE
        layoutNewPassword.visibility = View.GONE

        btnAuth.setOnClickListener {
            val password = etPassword.text.toString().trim()

            if(password.isEmpty()){
                etPassword.error = "Password harus diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            user?.let {
                val userCredential = EmailAuthProvider.getCredential(it.email!!,password)
                it.reauthenticate(userCredential).addOnCompleteListener {
                    if(it.isSuccessful){
                        layoutPassword.visibility = View.GONE
                        layoutNewPassword.visibility = View.VISIBLE
                    }else if(it.exception is FirebaseAuthInvalidCredentialsException){
                        etPassword.error = "Password salah"
                        etPassword.requestFocus()
                    }else{
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            btnUpdate.setOnClickListener {
                val newPassword = etNewPassword.text.toString().trim()
                val newPasswordConfirm = etNewPasswordConfirm.text.toString().trim()

                if(newPassword.isEmpty() || newPassword.length < 6){
                    etNewPassword.error = "Password harus lebih dari 6 karakter"
                    etNewPassword.requestFocus()
                    return@setOnClickListener
                }

                if (newPassword != newPasswordConfirm){
                    etNewPasswordConfirm.error = "Password salah"
                    etNewPasswordConfirm.requestFocus()
                    return@setOnClickListener
                }

                user?.updatePassword(newPassword)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Intent(this@ChangePasswordActivity, HomeActivity::class.java).also { intent ->
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            Toast.makeText(this,"Password berhasil diganti",Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}