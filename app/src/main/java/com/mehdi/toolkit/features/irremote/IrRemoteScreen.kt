package com.mehdi.toolkit.features.irremote

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun IrRemoteScreen(
    onBackClick: () -> Unit, // <--- This is the parameter that was missing!
    onHomeClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("IR Remote") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        // Note: AutoMirrored icon handles Left-to-Right vs Right-to-Left languages automatically
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = onHomeClick,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("IR Blaster Controls go here")
        }
    }
}