package com.composebasics.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composebasics.R
import com.composebasics.ui.theme.BackgroundGray

@Composable
fun ComposeBasics() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundGray
        ) {
            val scroll = rememberScrollState()
            Row(modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scroll),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Screen(
                    name = stringResource(id = R.string.greeting_card),
                    screen = {
                        GreetingCardScreen(
                            name = stringResource(
                                id = R.string.name
                            )
                        )
                    }
                )

                Screen(
                    name = stringResource(R.string.happy_birthday),
                    screen = {
                        HappyBirthdayScreen(
                            message = stringResource(id = R.string.happy_birthday_text),
                            from = stringResource(id = R.string.signature_text)
                        )
                    }
                )

                Screen(
                    name = stringResource(R.string.quadrants),
                    screen = {
                        QuadrantsScreen()
                    }
                )

                Screen(
                    name = stringResource(R.string.article),
                    screen = {
                        ArticleScreen()
                    }
                )

                Screen(
                    name = stringResource(R.string.task_manager),
                    screen = {
                        TaskManagerScreen()
                    }
                )

                Screen(
                    name = stringResource(R.string.business_card),
                    screen = {
                        BusinessCardScreen()
                    }
                )

            }
        }
    }
}

@Composable
private fun Screen(
    name: String,
    screen: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp, 500.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Text(
                text = name,
                modifier = Modifier.padding(4.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            screen()

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ComposeBasicsPreview() {
    ComposeBasics()
}