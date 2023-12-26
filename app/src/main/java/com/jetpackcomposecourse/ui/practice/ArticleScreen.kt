package com.jetpackcomposecourse.ui.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState()
    Column(modifier = modifier){
        Image(
            painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Column(
            modifier = modifier.verticalScroll(scroll)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Justify
            )

            Text(
                text = stringResource(id = R.string.large_text),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticlePreview() {
    JetpackComposeCourseTheme {
        ArticleScreen()
    }
}