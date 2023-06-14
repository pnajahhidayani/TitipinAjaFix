package com.example.titipinajamyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.home_toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi PostAdapter
        postAdapter = PostAdapter()

        // Set adapter pada RecyclerView
        recyclerView.adapter = postAdapter

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "my-database")
            .build()


        // Ambil data posting dari database
        lifecycleScope.launch {
            val postings = db.postingDao().getAllPostings()
            postAdapter.setPostings(postings)
        }

    }
}