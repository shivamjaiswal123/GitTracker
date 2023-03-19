package com.example.githubreponew

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.githubreponew.architecture.RepoViewModel
import kotlinx.android.synthetic.main.activity_add_repo.*

class AddRepoActivity : AppCompatActivity() {
    lateinit var viewmodel: RepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_repo)
        supportActionBar?.hide()
        viewmodel = ViewModelProvider(this)[RepoViewModel::class.java]

        btn_add.setOnClickListener {
            val owner = et_owner.text.toString()
            val repo = et_repo.text.toString()
            fetchRepoFromAPI(owner, repo, this)
        }
    }
    //fetch Repo from API
    private fun fetchRepoFromAPI(owner: String, repo: String, context: Context) {
        if(inputCheck(owner, repo)){
            viewmodel.fetchRepo(owner, repo, context)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inputCheck(owner: String, repo: String): Boolean {
        if(owner.isEmpty() || repo.isEmpty()){
            Toast.makeText(this, "Please fill all the fields !!!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}