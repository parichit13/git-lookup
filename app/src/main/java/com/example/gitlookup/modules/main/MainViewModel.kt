package com.example.gitlookup.modules.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitlookup.models.GithubUser
import com.example.gitlookup.models.Result
import com.example.gitlookup.network.GithubRepository
import com.example.gitlookup.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(private val githubRepo: GithubRepository) : ViewModel(){
    private val TAG = MainViewModel::class.java.simpleName
    val showMainProgress = MutableLiveData<Boolean>()
    val errorEvent = SingleLiveEvent<Boolean>()
    val liveUser = MutableLiveData<GithubUser>()
    val successEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun searchUser(username: String) {
        showMainProgress.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val result = githubRepo.getUser(username)
            showMainProgress.postValue(false)
            when(result) {
                is Result.Success -> {
                    Timber.d("Success")
                    liveUser.postValue(result.data)
                    successEvent.postValue(true)
                }
                is Result.Error -> {
                    Timber.d("Error")
                    Timber.e(result.error)
                    errorEvent.postValue(true)
                }
            }
        }
    }
}