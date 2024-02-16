package com.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposecourse.ui.practice.replyapp.ReplyApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(activity = this)
            ReplyApp(
                windowSize = windowSize.widthSizeClass
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JetpackComposeCoursePreview() {
    ReplyApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}