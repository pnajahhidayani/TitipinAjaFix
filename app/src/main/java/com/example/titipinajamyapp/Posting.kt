package com.example.titipinajamyapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postings")

data class Posting (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String
    )