package com.example.uicomponentsdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight.Companion.Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Layout Components", fontWeight = Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Column Layout (Vertical)")
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 1", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 2", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 3", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Row Layout (Horizontal)")
            Row(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text("A", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text("B", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    Text("C", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Combined Column and Row")
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Row 1, Col 1")
                    Text("Row 1, Col 2")
                    Text("Row 1, Col 3")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Row 2, Col 1")
                    Text("Row 2, Col 2")
                    Text("Row 2, Col 3")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Box Layout (Overlapping)")
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Red)
                        .align(Alignment.TopStart)
                ) {
                    Text("Top", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                        .align(Alignment.BottomEnd)
                ) {
                    Text("Bottom", color = Color.White)
                }
            }
        }
    }
}