package com.example.titipinajamyapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var fullNameEditText: TextInputEditText
    private lateinit var userNameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var signupButton: Button
    private lateinit var signinLinkButton: Button

    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullNameEditText = findViewById(R.id.fullname_signup)
        userNameEditText = findViewById(R.id.username_signup)
        emailEditText = findViewById(R.id.email_signup)
        passwordEditText = findViewById(R.id.password_signup)
        signupButton = findViewById(R.id.signupBtn)
        signinLinkButton = findViewById(R.id.signupLinkBtn)

        progressDialog = ProgressDialog(this@SignupActivity)
        mAuth = FirebaseAuth.getInstance()
        usersRef = FirebaseDatabase.getInstance().reference.child("Users")

        signupButton.setOnClickListener {
            createNewAccount()
        }

        signinLinkButton.setOnClickListener {
            startActivity(Intent(this@SignupActivity, SigninActivity::class.java))
        }
    }

    private fun createNewAccount() {
        val fullName = fullNameEditText.text.toString().trim()
        val userName = userNameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(this, "Full name is required", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()
            return
        }

        progressDialog.setTitle("Creating New Account")
        progressDialog.setMessage("Please wait, this may take a while..")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUserID = mAuth.currentUser?.uid
                    val userMap = HashMap<String, Any>()
                    userMap["uid"] = currentUserID!!
                    userMap["fullName"] = fullName
                    userMap["userName"] = userName
                    userMap["email"] = email

                    usersRef.child(currentUserID).setValue(userMap)
                        .addOnCompleteListener { innerTask ->
                            if (innerTask.isSuccessful) {
                                progressDialog.dismiss()
                                Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()

                                val intent = Intent(this@SignupActivity, SigninActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                                finish()
                            } else {
                                val message = innerTask.exception?.message
                                Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
                                progressDialog.dismiss()
                            }
                        }
                } else {
                    val message = task.exception?.message
                    Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            }
    }
}
