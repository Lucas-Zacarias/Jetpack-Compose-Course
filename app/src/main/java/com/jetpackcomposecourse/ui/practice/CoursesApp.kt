package com.jetpackcomposecourse.ui.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.DataSource
import com.jetpackcomposecourse.data.model.Topic
import com.jetpackcomposecourse.ui.theme.BackgroundCardCourseItem
import com.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

@Composable
fun CoursesApp() {
    CoursesList(
        DataSource.topics
    )
}

@Composable
private fun CoursesList(
    topics: List<Topic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(topics) { topic ->
            CourseItem(
                topic = topic,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun CourseItem(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundCardCourseItem
        )
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.topicImage),
                contentDescription = stringResource(id = topic.topicName),
                modifier = Modifier
                    .size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(id = topic.topicName),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(color = Color.Black),
                        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                    )
                    Text(
                        text = "${topic.numberOfCourses}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoursesAppPreview() {
    JetpackComposeCourseTheme {
        CoursesApp()
    }
}