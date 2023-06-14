package com.example.titipinajamyapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Posting::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postingDao(): PostingDao
}