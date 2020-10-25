package com.example.gitlookup.modules.main.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitlookup.network.GithubRepository

class SearchViewModel @ViewModelInject constructor(private val githubRepo: GithubRepository) : ViewModel() {
    val showMainProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    val success = MutableLiveData<Boolean>()
}