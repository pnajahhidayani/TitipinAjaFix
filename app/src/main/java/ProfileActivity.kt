package com.example.titipinajamyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.titipinajamyapp.EditProfileActivity
import com.example.titipinajamyapp.viewModel.AddPostActivity
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

public class ProfileActivity  : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var db: AppDatabase
    private lateinit var nameText: TextView
    private lateinit var emailText: TextView
    private lateinit var idText: TextView
    private lateinit var button: Button
    var name="null"
    var email="null"
    var password=null
    var providerId ="null"
    var username=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                providerId = profile.providerId.toString()


                // Name, email address, and profile photo Url
                name = profile.displayName.toString()
                email = profile.email.toString()

            }
        }
        Log.d("nama", name)

        nameText = findViewById(R.id.name)
        emailText = findViewById(R.id.email)
        button = findViewById(R.id.button)
        nameText.setText(name)
        emailText.setText(email)

        button.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, EditProfileActivity::class.java))
        }


    }
}

