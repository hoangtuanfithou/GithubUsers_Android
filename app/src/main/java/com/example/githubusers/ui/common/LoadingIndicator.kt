package com.example.githubusers.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp? = null
) {
    Box(
        modifier = if (size != null) {
            modifier.fillMaxWidth().padding(16.dp)
        } else {
            modifier.fillMaxSize()
        },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = modifier
            .size(size ?: 50.dp))
    }
}
