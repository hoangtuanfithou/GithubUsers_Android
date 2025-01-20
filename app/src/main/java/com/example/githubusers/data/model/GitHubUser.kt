import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    var location: String? = null,
    var followers: Int = 0,
    var following: Int = 0
)
