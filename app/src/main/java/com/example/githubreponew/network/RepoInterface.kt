package com.example.githubreponew.network

import com.example.githubreponew.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoInterface {
    @GET("/repos/{owner}/{repo}")
    suspend fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String): Response<Repository>
}