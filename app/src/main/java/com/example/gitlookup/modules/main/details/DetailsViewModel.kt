package com.example.gitlookup.modules.main.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitlookup.models.GithubRepo
import com.example.gitlookup.models.Result
import com.example.gitlookup.network.GithubRepository
import com.example.gitlookup.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel @ViewModelInject constructor(private val githubRepo: GithubRepository) : ViewModel() {
    val errorEvent = SingleLiveEvent<Boolean>()
    val showLoading = MutableLiveData<Boolean>()
    val livePublicRepos = MutableLiveData<List<GithubRepo>>()

    fun fetchPublicRepos(userId: String) {
        showLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val result = githubRepo.getRepos(userId)
            showLoading.postValue(false)
            when (result) {
                is Result.Success -> {
                    livePublicRepos.postValue(result.data)
                }
                is Result.Error -> {
                    Timber.e(result.error)
                    errorEvent.postValue(true)
                }
            }
        }
    }
}