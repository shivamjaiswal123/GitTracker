package com.example.githubreponew.architecture

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.githubreponew.model.Repository
import com.example.githubreponew.network.RetrofitHelper
import kotlinx.coroutines.*

class RepoRepository {
    companion object {
        private var repodatabase: RepoDatabase? = null

        private fun initializeDB(context: Context): RepoDatabase? {
            return RepoDatabase.getInstance(context)
        }

        fun getAllRepo(context: Context): LiveData<List<Repository>> {
            repodatabase = initializeDB(context)

            return repodatabase!!.repoDao().getAllRepos()
        }

        //fetch from repos API
        fun getRepoFromAPI(owner: String, repo: String, context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitHelper.service.getRepository(owner, repo)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    val repoEntity = Repository(body.name, body.description, body.html_url)

                    //insert repo in room database
                    repodatabase = RepoDatabase.getInstance(context)
                    repodatabase!!.repoDao().insert(repoEntity)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Repository Added", Toast.LENGTH_LONG).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Repository does not exist", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
