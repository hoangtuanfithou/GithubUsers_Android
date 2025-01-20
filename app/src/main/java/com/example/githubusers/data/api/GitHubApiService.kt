import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int = 20,
        @Query("since") since: Int = 0
    ): List<GitHubUser>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): GitHubUser
}
