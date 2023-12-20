package com.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import com.jetpackcomposecourse.ui.theme.Quadrant1
import com.jetpackcomposecourse.ui.theme.Quadrant2
import com.jetpackcomposecourse.ui.theme.Quadrant3
import com.jetpackcomposecourse.ui.theme.Quadrant4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quadrants()
                }
            }
        }
    }
}

@Composable
private fun Quadrants() {
    Column(modifier = Modifier) {
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                modifier = Modifier.weight(1f),
                color = Quadrant1,
                title = stringResource(id = R.string.title_quadrant_1),
                description = stringResource(id = R.string.description_quadrant_1)
            )

            Quadrant(
                modifier = Modifier.weight(1f),
                color = Quadrant2,
                title = stringResource(id = R.string.title_quadrant_2),
                description = stringResource(id = R.string.description_quadrant_2)
            )

        }
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                modifier = Modifier.weight(1f),
                color = Quadrant3,
                title = stringResource(id = R.string.title_quadrant_3),
                description = stringResource(id = R.string.description_quadrant_3)
            )

            Quadrant(
                modifier = Modifier.weight(1f),
                color = Quadrant4,
                title = stringResource(id = R.string.title_quadrant_4),
                description = stringResource(id = R.string.description_quadrant_4)
            )
        }
    }
}

@Composable
private fun Quadrant(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    description: String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = description,
            textAlign = TextAlign.Justify
        )

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackComposeCourseTheme {
        Quadrants()
    }
}