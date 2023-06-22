package com.example.titipinajamyapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostingDao {
    @Insert
    suspend fun insert(posting: Posting): Long

    @Query("SELECT * FROM postings")
    fun getAllPostings(): LiveData<Posting>
//    suspend fun getAllPostings(): <List<Posting>>
}
