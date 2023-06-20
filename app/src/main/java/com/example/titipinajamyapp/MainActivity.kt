package com.example.titipinajamyapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.titipinajamyapp.viewModel.AddPostActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val buttomNavigationView: ButtomNavigationView = findViewById(R.id.ButtomNavigationView)
        bottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            onNavigationItemSelected(menuItem)
        }
    }
    fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_add_post -> {
                val intent = Intent(this, AddPostActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }else ->
            return false
        }
        val btnAddPostActivity: Button = findViewById(R.id.nav_add_post)

        btnAddPostActivity.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }
    }
}




//        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.nav_home -> {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.nav_add_post -> {
//                    val intent = Intent(this, AddPostActivity::class.java)
//                    startActivity(intent)
//                    return@setOnNavigationItemSelectedListener true
//                }
//                // Tambahkan case untuk item-menu lain jika diperlukan
//                // ...
//                else -> return@setOnNavigationItemSelectedListener false
//            }
//        }




//        bottomNavigationView.setOnNavigationItemSelectedListener
//        NavigationBarView.OnItemSelectedListener