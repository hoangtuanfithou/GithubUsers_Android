package com.example.githubusers.ui.detail.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FollowInfoRow(followers: Int, following: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FollowInfoView(
            count = followers,
            label = "Followers")
        FollowInfoView(
            icon = Icons.Filled.Notifications,
            count = following,
            label = "Following"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFollowInfoRow() {
    FollowInfoRow(
        followers = 123,
        following = 456)
}