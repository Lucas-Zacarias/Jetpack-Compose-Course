package com.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposecourse.ui.practice.replyapp.ReplyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReplyApp()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JetpackComposeCoursePreview() {
    ReplyApp()
}