package com.example.titipinajamyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
//    private var postings: List<Posting> = emptyList()
private var postings: LiveData<List<Posting>> = MutableLiveData<List<Posting>>()


    fun setPostings(postings: LiveData<List<Posting>>) {
        this.postings = postings
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle: TextView = itemView.findViewById(R.id.title_edittext)
        val textViewContent: TextView = itemView.findViewById(R.id.edittext_postcontent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posting = postings.value?.get(position)
        holder.textViewTitle.text = posting?.title
        holder.textViewContent.text = posting?.content
    }
    override fun getItemCount(): Int {
        return postings.value?.size ?: 0
    }

}