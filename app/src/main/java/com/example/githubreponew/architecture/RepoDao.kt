package com.example.githubreponew.architecture

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubreponew.model.Repository

@Dao
interface RepoDao {
    @Insert
    suspend fun insert(repo: Repository)

    @Query("Select * from Repository")
    fun getAllRepos(): LiveData<List<Repository>>
}