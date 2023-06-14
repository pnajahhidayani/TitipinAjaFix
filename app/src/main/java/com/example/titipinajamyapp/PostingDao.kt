package com.example.titipinajamyapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostingDao {
    @Insert
    suspend fun insert(posting: Posting)

    @Query("SELECT * FROM postings")
    fun getAllPostings(): LiveData<List<Posting>>
//    suspend fun getAllPostings(): List<Posting>
}
