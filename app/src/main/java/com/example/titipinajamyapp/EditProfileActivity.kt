package com.example.titipinajamyapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class EditProfileActivity    : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var db: AppDatabase
    private lateinit var fullNameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    private lateinit var button: Button
    var name="null"
    var emaill="null"
    var password=null
    var providerId ="null"
    var username=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                providerId = profile.providerId.toString()


                // Name, email address, and profile photo Url
                name = profile.displayName.toString()
                emaill = profile.email.toString()

            }
        }
        Log.d("nama", name)

        fullNameEditText = findViewById(R.id.fullname_signup)
        emailEditText = findViewById(R.id.email_signup)
        passwordEditText = findViewById(R.id.password_signup)
        button = findViewById(R.id.button)


        button.setOnClickListener {

            val fullName = fullNameEditText.text.toString().trim()
            val emails = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            var a = 0

            if (TextUtils.isEmpty(fullName)) {
                Toast.makeText(this, "Full name is required", Toast.LENGTH_LONG).show()
                a=1
            }


            if (TextUtils.isEmpty(emails)) {
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()
                a=1
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()
                a=1
            }
            if(a==1){
                val user = Firebase.auth.currentUser

                val profileUpdates = userProfileChangeRequest {
                    displayName = fullName
                }
                Toast.makeText(this, "Nama Sudah Diubah", Toast.LENGTH_LONG).show()
                user!!.updatePassword(password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password Sudah Diubah", Toast.LENGTH_LONG).show()
                        }
                    }
                user!!.updateEmail(emails)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Email Sudah Diubah", Toast.LENGTH_LONG).show()
                        }
                    }

            }

            startActivity(Intent(this@EditProfileActivity, ProfileActivity::class.java))
        }


    }
}