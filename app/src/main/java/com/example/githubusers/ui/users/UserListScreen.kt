package com.example.githubusers.ui.users
import MainViewModel
import UserListItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.Dp
import com.example.githubusers.ui.common.LoadingIndicator

@Composable
fun UserListScreen(
    viewModel: MainViewModel,
    onUserClick: (String) -> Unit
) {
    val users by viewModel.users.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (users.isEmpty()) {
            LoadingIndicator()
        } else {
            LazyColumn {
                items(users) { user ->
                    UserListItem(
                        user = user,
                        onClick = { onUserClick(user.login) }
                    )
                }

                // Add loading indicator at bottom and trigger load more
                item {
                    if (users.isNotEmpty()) {
                        LaunchedEffect(Unit) {
                            viewModel.loadMoreUsers()
                        }
                        LoadingIndicator(size = 30.dp)
                    }
                }
            }
        }
    }
}
