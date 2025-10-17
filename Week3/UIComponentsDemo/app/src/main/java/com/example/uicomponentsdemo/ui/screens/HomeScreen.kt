package com.example.uicomponentsdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight.Companion.Bold

@Composable
fun HomeScreen(
    onTextClick: () -> Unit,
    onImageClick: () -> Unit,
    onInputClick: () -> Unit,
    onLayoutClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "UI Components List",
                fontWeight = Bold,
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = onTextClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Text Components")
            }

            Button(
                onClick = onImageClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Image Components")
            }

            Button(
                onClick = onInputClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Input Components")
            }

            Button(
                onClick = onLayoutClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Layout Components")
            }
        }
    }
}