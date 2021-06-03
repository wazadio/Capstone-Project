package com.example.transconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.transconnect.feedback.FeedbackActivity
import com.example.transconnect.fragment.AccountFragment
import com.example.transconnect.fragment.BusFragment
import com.example.transconnect.fragment.PointFragment
import com.example.transconnect.register.ChangePasswordActivity
import com.example.transconnect.register.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val accountFragment = AccountFragment()
        val busFragment = BusFragment()
        val pointFragment = PointFragment()


        makeCurrentFragment(accountFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_account -> makeCurrentFragment(accountFragment)
                R.id.bus_ic -> makeCurrentFragment(busFragment)
                R.id.point_ic -> makeCurrentFragment(pointFragment)

            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                auth.signOut()
                Intent(this@HomeActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                return true
            }

            R.id.chpassword -> {
                Intent(this@HomeActivity, ChangePasswordActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.feedback_nv -> {
                Intent(this@HomeActivity, FeedbackActivity::class.java).also {
                    startActivity(it)
                }
            }
            else -> return true
        }
        return true
    }
}