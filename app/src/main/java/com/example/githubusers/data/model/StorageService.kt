package com.example.githubusers.data.model

import GitHubUser
import android.content.Context
import com.example.githubusers.app.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StorageService(private val context: Context? = App.context) {
    private val prefs = context?.getSharedPreferences("github_users", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadCachedUsers(): List<GitHubUser> {
        val cachedJson = prefs?.getString("cached_users", null) ?: return emptyList()
        return try {
            gson.fromJson<List<GitHubUser>>(cachedJson, object : TypeToken<List<GitHubUser>>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun cacheUsers(users: List<GitHubUser>) {
        try {
            val json = gson.toJson(users)
            prefs?.edit()?.putString("cached_users", json)?.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}