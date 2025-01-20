import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.githubusers.ui.detail.subviews.BlogInfoView
import com.example.githubusers.ui.detail.UserDetailViewModel
import com.example.githubusers.ui.detail.subviews.UserInfoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubusers.ui.common.LoadingIndicator
import com.example.githubusers.ui.detail.subviews.FollowInfoRow

@Composable
fun UserDetailScreen(
    username: String,
    viewModel: UserDetailViewModel
) {
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UserInfoView(
            avatarUrl = user?.avatarUrl,
            login = user?.login,
            location = user?.location
        )

        FollowInfoRow(
            followers = user?.followers ?: 0,
            following = user?.following ?: 0
        )

        BlogInfoView(url = user?.htmlUrl ?: "")

        if (user == null) {
            LoadingIndicator(size = 30.dp)
        }
    }
}
