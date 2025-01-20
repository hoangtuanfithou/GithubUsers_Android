package com.example.githubusers.ui.detail.subviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FollowInfoView(icon: ImageVector = Icons.Filled.Person,
                   count: Int,
                   label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Gray, shape = CircleShape)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(imageVector = icon,
                contentDescription = "Follow Icon",
                modifier = Modifier.size(50.dp)
                    .align(Alignment.Center),
                tint = Color.White
            )
        }
        Text(
            text = "$count+",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFollowStats() {
    FollowInfoView(
        count = 123,
        label = "Followers")
}
