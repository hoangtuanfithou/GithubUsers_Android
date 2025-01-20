import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubusers.ui.detail.UserDetailViewModel
import com.example.githubusers.ui.users.UserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val route = currentBackStackEntry.value?.destination?.route
                    Text(text = if (route?.startsWith("user/") == true) "User Details" else "Github Users")
                },
                navigationIcon = {
                    val route = currentBackStackEntry.value?.destination?.route
                    if (route?.startsWith("user/") == true) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "users", Modifier.padding(innerPadding)) {
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
}