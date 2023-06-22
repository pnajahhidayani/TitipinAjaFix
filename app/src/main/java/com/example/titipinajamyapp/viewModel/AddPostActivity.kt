package com.example.titipinajamyapp.viewModel

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.titipinajamyapp.*
//import com.example.titipinajamyapp.databinding.ActivityAddPostBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.android.synthetic.main.activity_add_post.*


class AddPostActivity(db: PostingDao) : AppCompatActivity() {
    val data = this.db.getAllPostings()

//    private lateinit var editTextTitle: EditText
//    private lateinit var editTextContent: EditText
//    private lateinit var buttonAddPosting: Button

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)


//        editTextTitle = findViewById(R.id.title_edittext)
//        editTextContent = findViewById(R.id.edittext_postcontent)
//        buttonAddPosting = findViewById(R.id.button_post)

        val buttonAddPosting: Button = findViewById(R.id.button_post)
        val editTextTitle: EditText = findViewById(R.id.title_edittext)
        val editTextContent: EditText = findViewById(R.id.edittext_postcontent)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "my-database")
            .build()

        buttonAddPosting.setOnClickListener {
            val title = editTextTitle.text.toString()
            val content = editTextContent.text.toString()

                if (title.isNotEmpty() && content.isNotEmpty()) {

                    GlobalScope.launch {
                        (Dispatchers.IO)
                        val posting = Posting(
                            title = title,
                            content = content)
//                        val postingDao: PostingDao = db.insert(posting: Posting)
//                        PostingDao.insert(posting)
                        db.insert(posting)
                    }
                    Toast.makeText(this, "Posting added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Title and Content cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }


//        val postingsLiveData: LiveData<List<Posting>> = db.postingDao().getAllPostings()
//        postingsLiveData.observe(this) { postings ->
//            for (posting in postings) {
//                Log.d("Posting Title", posting.title)
//            }
//        }
    }
}



//    private fun showProgressBar() {
//        progressBar.visibility = View.GONE
//    }
//
//    private fun hideProgressBar() {
//        progressBar.visibility = View.GONE
//    }
//
//    private fun showMessage(message: String) {
//        messageTextView.text = message
//        messageTextView.visibility = View.VISIBLE
//    }
//
//    fun AddPostToFireStore(){
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
//        progressBar.visibility = View.GONE
//
//        var username: String? = null
//        val DB = FirebaseFirestore.getInstance()
//        val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
//        val docRef = DB.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
//
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if(document != null) {
//                    username = document.getString("username")
//                    val ForumPosts: MutableMap<String, Any> = HashMap()
//                    ForumPosts["comment_number"] = 0
//                    ForumPosts["date"] = simpleDateFormat.format(Date())
//                    ForumPosts["title"] = binding.titleEdittext.text.toString().trim()
//                    ForumPosts["username"] = username!!
//                    ForumPosts["view_number"] = 0
//                    DB.collection("ForumPosts")
//                        .add(ForumPosts)
//                        .addOnSuccessListener {
//                            progressBar.visibility = View.GONE
//                            val snackbar = Snackbar
//                                .make(coordinator, "Content Posted successfully", Snackbar.LENGTH_LONG)
//                            snackbar.show()
//                            val handler = Handler()
//                            handler.postDelayed({
//                                finish()
//                            }, 1500)
//                        }
//                        .addOnFailureListener { e ->
//                            Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
//                            progressBar.visibility = View.GONE
//                            finish()
//                        }
//                } else {
//                    Toast.makeText(this, "Problem occurred", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "Error", exception)
//            }
//    }


//    fun AddPostToFireStore()
//    {
//        val progressDialog = ProgressDialog(this)
//        progressDialog.setTitle("Please Wait")
//        progressDialog.setMessage("Posting Content")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//        progressDialog.dismiss()
//
//        var username: String? = null
//        val DB = FirebaseFirestore.getInstance()
//        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val docRef = DB.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
//
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    username = document.getString("username")
//                    val ForumPosts: MutableMap<String, Any> = HashMap()
//                    ForumPosts["comment_number"] = 0
//                    ForumPosts["date"] = simpleDateFormat.format(Date())
//                    ForumPosts["title"] = binding.titleEdittext.text.toString().trim()
//                    ForumPosts["username"] = username!!
//                    ForumPosts["view_number"] = 0
//                    DB.collection("ForumPosts")
//                        .add(ForumPosts)
//                        .addOnSuccessListener {
//                            progressDialog.dismiss()
//                            val snackbar = Snackbar
//                                .make(coordinator, "Content Posted sucesfully", Snackbar.LENGTH_LONG)
//                            snackbar.show()
//                            val handler = Handler()
//                            handler.postDelayed({
//                                finish()
//                            }, 1500)
//                        }
//                        .addOnFailureListener{e ->
//                            Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
//                            progressDialog.dismiss()
//                            finish()
//                        }
//                }
//                else
//                {
//                    Toast.makeText(this, "Problem occured", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "Error", exception)
//            }
//    }
//        binding.buttonPost.setOnClickListener{
//           AddPostToFireStore()
//        }
//
//        progressBar = findViewById(R.id.progressBar)
//        messageTextView = findViewById(R.id.messageTextView)
//
//        showMessage("Loading..")
//        showProgressBar()