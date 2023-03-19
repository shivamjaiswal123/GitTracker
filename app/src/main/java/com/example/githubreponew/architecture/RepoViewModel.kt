package com.example.githubreponew.architecture

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubreponew.model.Repository

class RepoViewModel: ViewModel() {

    fun fetchRepo(owner: String, repo: String, context: Context) {
        RepoRepository.getRepoFromAPI(owner, repo, context)
    }

    fun showAllRepo(context: Context): LiveData<List<Repository>> {
        return RepoRepository.getAllRepo(context)
    }
}