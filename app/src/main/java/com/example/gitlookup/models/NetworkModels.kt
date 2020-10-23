package com.example.gitlookup.models

import com.google.gson.annotations.SerializedName

sealed class Result<out T> {
    class Success<out T>(val data: T?) : Result<T>()
    class Error(val error: Exception) : Result<Nothing>()
}

data class GithubUser(
    val id: Int?,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerializedName("public_repos")
    val publicRepos: String?,
    val followers: Int?,
    val following: Int?
)

data class GithubRepo(
    val id: Int?,
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("html_url")
    val url: String?,
    val description: String?
)