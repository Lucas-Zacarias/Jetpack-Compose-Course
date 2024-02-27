package com.lemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemon.R
import com.lemon.ui.theme.BackgroundLemon
import com.lemon.ui.theme.LemonTheme
import com.lemon.ui.theme.LemonadeYellow

@Composable
fun Lemonade() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTitle()
        LemonImages(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
private fun AppTitle() {
    Text(
        text = stringResource(R.string.lemonade),
        color = Color.Black,
        modifier = Modifier
            .background(color = LemonadeYellow)
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
private fun LemonImages(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(1) }
        when(step) {
            1 -> {
                LemonImage(
                    modifier,
                    R.drawable.lemon_tree,
                    R.string.tap_the_lemon_tree_to_select_a_lemon,
                    clickEvent = {
                        step = 2
                    }
                )
            }

            2 -> {
                var requiredTaps = (2..4).random()
                LemonImage(
                    modifier,
                    R.drawable.lemon_squeeze,
                    R.string.keep_tapping_the_lemon_to_squeeze_it,
                    clickEvent = {
                        requiredTaps--
                        if (requiredTaps == 0) {
                            step = 3
                        }
                    }
                )
            }

            3 -> {
                LemonImage(
                    modifier,
                    R.drawable.lemon_drink,
                    R.string.tap_the_lemonade_to_drink_it,
                    clickEvent = {
                        step = 4
                    }
                )
            }

            4 -> {
                LemonImage(
                    modifier,
                    R.drawable.lemon_restart,
                    R.string.tap_the_empty_glass_to_start_again,
                    clickEvent = {
                        step = 1
                    }
                )
            }
        }

}

@Composable
private fun LemonImage(
    modifier: Modifier,
    imageId: Int,
    descriptionId: Int,
    clickEvent: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = modifier
                .background(color = BackgroundLemon, shape = RoundedCornerShape(20.dp))
                .padding(20.dp)
                .wrapContentSize()
                .clickable {
                    clickEvent()
                }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = descriptionId),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LemonadeAppPreview() {
    LemonTheme {
        Lemonade()
    }
}
