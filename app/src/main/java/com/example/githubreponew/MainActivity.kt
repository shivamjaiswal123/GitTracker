package com.example.githubreponew

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubreponew.adapter.RepoAdapter
import com.example.githubreponew.architecture.RepoViewModel
import com.example.githubreponew.model.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: RepoViewModel
    lateinit var adapter: RepoAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this)[RepoViewModel::class.java]

        val repoList = mutableListOf<Repository>()
        rv_repolist.layoutManager = LinearLayoutManager(this)
        adapter = RepoAdapter(this, repoList)

        viewmodel.showAllRepo(this).observe(this, Observer {
            repoList.clear()
            repoList.addAll(it)
            adapter.notifyDataSetChanged()

            if (repoList.isEmpty()) {
                tv_no_repos.visibility = View.VISIBLE
                btn_add_repo.visibility = View.VISIBLE
            } else {
                tv_no_repos.visibility = View.GONE
                btn_add_repo.visibility = View.GONE
            }
        })
        rv_repolist.adapter = adapter

        btn_add_repo.setOnClickListener {
            val intent = Intent(this, AddRepoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, AddRepoActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}