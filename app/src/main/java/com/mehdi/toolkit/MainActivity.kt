package com.mehdi.toolkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.entryProvider
// import androidx.navigation3.runtime.entry // usually not needed with DSL, but if it fails, add it.
import androidx.navigation3.ui.NavDisplay
import com.mehdi.toolkit.features.imageresizer.ImageResizerScreen
import com.mehdi.toolkit.features.transcriber.TranscriberScreen
//import androidx.navigation3.ui.entry
import com.mehdi.toolkit.features.irremote.IrRemoteScreen
import com.mehdi.toolkit.ui.theme.MyToolkitTheme
import kotlinx.serialization.Serializable

@Serializable data object HomeKey : NavKey
@Serializable data object IrRemoteKey : NavKey
@Serializable data object ImgResizerKey : NavKey
@Serializable data object TranscriberKey : NavKey

data class Tool(val name: String, val icon: ImageVector, val key: NavKey)

val toolsList = listOf(
    Tool("IR Remote", Icons.Default.Tv, IrRemoteKey),
    Tool("Img Resizer", Icons.Default.Build, ImgResizerKey),
    Tool("Transcriber", Icons.Default.Create, TranscriberKey)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyToolkitTheme {
                val backStack = rememberNavBackStack(HomeKey)

                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        // FIX for API 35 Crash: Use removeAt instead of removeLast
                        if (backStack.isNotEmpty()) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    entryProvider = entryProvider {
                        entry<HomeKey> {
                            ToolkitDashboard(
                                tools = toolsList,
                                onToolClick = { key -> backStack.add(key) }
                            )
                        }

                        entry<IrRemoteKey> {
                            IrRemoteScreen(
                                onBackClick = {
                                    if (backStack.isNotEmpty()) backStack.removeAt(backStack.lastIndex)
                                },
                                onHomeClick = {
                                    // THE MAGIC: Clear everything and put HomeKey back
                                    backStack.clear()
                                    backStack.add(HomeKey)
                                }
                            )
                        }

                        entry<ImgResizerKey> {
                            ImageResizerScreen(
                                onBackClick = {
                                    if (backStack.isNotEmpty()) {
                                        backStack.removeAt(backStack.lastIndex)
                                    }
                                }
                            )
                        }

                        entry<TranscriberKey> {
                            TranscriberScreen(
                                onBackClick = {
                                    if (backStack.isNotEmpty()) {
                                        backStack.removeAt(backStack.lastIndex)
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}

// ... (Keep your ToolkitDashboard and ToolCard functions below this)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitDashboard(tools: List<Tool>, onToolClick: (NavKey) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My Toolkit") }) }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tools) { tool ->
                ToolCard(tool, onClick = { onToolClick(tool.key) })
            }
        }
    }
}

@Composable
fun ToolCard(tool: Tool, onClick: () -> Unit) {
    Card(
        modifier = Modifier.height(150.dp).clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(tool.icon, contentDescription = tool.name, modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = tool.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}