package com.example.uicomponentsdemo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("TextDetail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 28.sp)) {
                        append("The ")
                    }
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 28.sp
                        )
                    ) {
                        append("quick")
                    }
                    withStyle(
                        style = SpanStyle(color = Color(0xFFFFB300), fontWeight = FontWeight.SemiBold, fontSize = 36.sp)
                    ) {
                        append(" Brown")
                    }
                    append("\n\n")
                    withStyle(style = SpanStyle(fontSize = 22.sp, letterSpacing = 6.sp)) {
                        append("fox ")
                    }
                    withStyle(style = SpanStyle(fontSize = 22.sp, letterSpacing = 10.sp)) {
                        append("j u m p s ")
                    }
                    withStyle(
                        style = SpanStyle(fontSize = 22.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                    ) {
                        append("over\n\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 20.sp, textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)) {
                        append(" lazy")
                    }
                    withStyle(style = SpanStyle(fontSize = 20.sp)) {
                        append(" dog.")
                    }
                },
                modifier = Modifier
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider(thickness = 2.dp, color = Color.LightGray, modifier = Modifier.padding(top = 12.dp))
        }
    }
}
