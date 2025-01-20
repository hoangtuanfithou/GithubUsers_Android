import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubusers.ui.detail.UserDetailViewModel
import com.example.githubusers.ui.users.UserListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "users") {
        composable("users") {
            val viewModel: MainViewModel = viewModel()
            UserListScreen(
                viewModel = viewModel,
                onUserClick = { username ->
                    navController.navigate("user/$username")
                }
            )
        }
        composable(
            "user/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: return@composable
            val viewModel: UserDetailViewModel = viewModel()
            LaunchedEffect(username) {
                viewModel.loadUserDetail(username)
            }
            UserDetailScreen(username = username, viewModel = viewModel)
        }
    }
}