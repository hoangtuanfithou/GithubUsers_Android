package com.example.githubusers.ui.detail

import GitHubApiService
import GitHubUser
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailViewModel(private val api: GitHubApiService = RetrofitClient.createService()) : ViewModel() {
    private val _user = MutableStateFlow<GitHubUser?>(null)
    val user = _user.asStateFlow()

    fun loadUserDetail(username: String) {
        viewModelScope.launch {
            try {
                val userDetail = api.getUserDetail(username)
                _user.value = userDetail
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace() // Log error
            }
        }
    }
}
