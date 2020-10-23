package com.example.gitlookup.network

import com.example.gitlookup.utils.apiHandler
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUser(userId: String) = apiHandler { apiService.getGithubUser(userId) }

    suspend fun getRepos(userId: String) = apiHandler { apiService.getPublicRepos(userId) }
}