package com.example.gitlookup.network

import com.example.gitlookup.models.GithubRepo
import com.example.gitlookup.models.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{userId}")
    suspend fun getGithubUser(
        @Path("userId")  userId: String
    ) : Response<GithubUser>

    @GET("users/{userId}/repos")
    suspend fun getPublicRepos(
        @Path("userId") userId: String
    ) : Response<List<GithubRepo>>

}