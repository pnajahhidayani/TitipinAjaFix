package com.example.titipinajamyapp.com.example.titipinajamyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.titipinajamyapp.PostingDao
import com.example.titipinajamyapp.viewModel.AddPostActivity

class AddPostActivityFactory (
    private val db: PostingDao
        ) : ViewModelProvider.Factory {
            @Suppress("unchecked_cast")
            override fun <T :ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AddPostActivity::class.java)) {
                    return AddPostActivity(db) as T
                }
                throw  IllegalArgumentException("Unknown ViewModel Class")
            }
        }