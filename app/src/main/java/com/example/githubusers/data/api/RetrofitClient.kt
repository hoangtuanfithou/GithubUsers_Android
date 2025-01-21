package com.example.githubusers.data.api
import GitHubApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"
    /// **Note:** Replace with github token https://github.com/settings/tokens or keep empty with limited access.
    private const val TOKEN = ""

    fun createService(): GitHubApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequestBuilder = originalRequest.newBuilder()

                // Only add the Authorization header if TOKEN is not empty
                if (TOKEN.isNotEmpty()) {
                    newRequestBuilder.header("Authorization", "token $TOKEN")
                }

                val newRequest = newRequestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }
}