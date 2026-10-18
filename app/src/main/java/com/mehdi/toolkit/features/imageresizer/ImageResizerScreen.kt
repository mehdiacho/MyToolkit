package com.mehdi.toolkit.features.imageresizer


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageResizerScreen(
    onBackClick: () -> Unit // <--- This is the parameter that was missing!
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Image Resizer") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        // Note: AutoMirrored icon handles Left-to-Right vs Right-to-Left languages automatically
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("Image Resizer Tools go here")
        }
    }
}