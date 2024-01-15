package com.jetpackcomposecourse.ui.practice

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.HeroesRepository.heroes
import com.jetpackcomposecourse.data.model.Hero
import com.jetpackcomposecourse.ui.theme.SuperheroesTheme

@Composable
fun SuperHeroesApp() {
    SuperheroesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SuperHeroes()
        }
    }
}

@Composable
fun SuperHeroes() {
    Scaffold(
        topBar = {
            SuperHeroesAppBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(heroes) { hero ->
                SuperHeroCard(
                    hero = hero,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuperHeroesAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.superheroes_app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun SuperHeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(16.dp)
        ) {
          Column(
              modifier = Modifier.weight(2f)
          ) {
              Text(
                  text = stringResource(id = hero.nameRes),
                  style = MaterialTheme.typography.displaySmall
              )
              Text(
                  text = stringResource(id = hero.descriptionRes),
                  style = MaterialTheme.typography.bodyLarge
              )
          }
          Spacer(modifier = Modifier.width(16.dp))
          Image(
              painter = painterResource(id = hero.imageRes),
              contentDescription = stringResource(id = hero.nameRes),
              modifier = Modifier
                  .size(72.dp)
                  .clip(RoundedCornerShape(8.dp))
          )
        }
    }
}

@Preview(name = "Super Heroes Light Mode",showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Super Heroes Dark Mode",showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SuperHeroesPreview() {
    SuperHeroesApp()
}