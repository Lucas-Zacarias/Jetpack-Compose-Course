package com.jetpackcomposecourse.ui.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.ui.theme.BackgroundArtwordButton
import com.jetpackcomposecourse.ui.theme.BackgroundArtworkApp
import com.jetpackcomposecourse.ui.theme.BackgroundArtworkDescription
import com.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

@Composable
fun SpaceArt() {
    var artwork by remember {
        mutableStateOf(artworks[0])
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BackgroundArtworkApp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(artwork) {
                "LOS GIRASOLES" -> Artwork(
                    image = R.drawable.los_girasoles,
                    title = R.string.los_girasoles,
                    artist = R.string.vincent_van_gogh,
                    year = R.string.los_girasoles_year_1888
                )
                "LA NOCHE ESTRELLADA" -> Artwork(
                    image = R.drawable.la_noche_estrellada,
                    title = R.string.la_noche_estrellada,
                    artist = R.string.vincent_van_gogh,
                    year = R.string.la_noche_estrellada_year_1889
                )
                "EL GRITO" -> Artwork(
                    image = R.drawable.el_grito,
                    title = R.string.el_grito,
                    artist = R.string.edvard_munch,
                    year = R.string.el_grito_year_1893
                )
            }
        }
        NavigationButtons(
            modifier = Modifier.align(Alignment.BottomCenter),
            moveNext = {
                artwork = if(artworks.indexOf(artwork) >= artworks.size-1) {
                    artworks.first()
                }else {
                    artworks[artworks.indexOf(artwork) + 1]
                }
            },
            movePrevious = {
                artwork = if(artworks.indexOf(artwork) == 0) {
                    artworks.last()
                }else {
                    artworks[artworks.indexOf(artwork) - 1]
                }
            }
        )
    }



}

@Composable
private fun Artwork(
    modifier: Modifier = Modifier,
    image: Int,
    title: Int,
    artist: Int,
    year: Int
) {
    Column {
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .shadow(elevation = 12.dp)
                .background(color = Color.White)
                .padding(32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(height = 400.dp, width = 600.dp)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = BackgroundArtworkDescription
            )
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ){
                Text(
                    text = stringResource(title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(artist),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(year),
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

    }
}

@Composable
private fun NavigationButtons(
    modifier: Modifier = Modifier,
    movePrevious: () -> Unit,
    moveNext: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundArtwordButton
            ),
            modifier = Modifier.size(height = 40.dp, width = 130.dp),
            onClick = { movePrevious() }
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundArtwordButton
            ),
            modifier = Modifier.size(height = 40.dp, width = 130.dp),
            onClick = { moveNext() }
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

private val artworks = listOf(
    "LOS GIRASOLES",
    "LA NOCHE ESTRELLADA",
    "EL GRITO"
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpaceArtPreview() {
    JetpackComposeCourseTheme {
        SpaceArt()
    }
}