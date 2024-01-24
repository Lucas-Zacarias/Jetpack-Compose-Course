package com.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposecourse.ui.practice.cupcakeapp.CupcakeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeApp()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JetpackComposeCoursePreview() {

}