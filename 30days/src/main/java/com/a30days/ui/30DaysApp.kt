package com.a30days.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a30days.R
import com.a30days.data.StretchingList.stretchingList
import com.a30days.model.Stretching

@Composable
fun DaysApp() {
    Stretching()
}

@Composable
private fun Stretching() {
    Scaffold(
        topBar = {
            StretchingAppTopBar()
        },
        modifier = Modifier.padding(horizontal = 8.dp)
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(stretchingList) { stretching ->
                val position = stretchingList.indexOf(stretching)
                val numberOfDay = position + 1
                StretchingCard(
                    stretching = stretching,
                    numberOfDay = numberOfDay,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun StretchingCard(
    stretching: Stretching,
    numberOfDay: Int,
    modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = modifier.fillMaxWidth()
    ) {
       Column(
           modifier = Modifier
               .padding(12.dp)
               .animateContentSize(
                   animationSpec = spring(
                       dampingRatio = Spring.DampingRatioHighBouncy,
                       stiffness = Spring.StiffnessVeryLow
                   )
               )
       ) {
           Text(
               text = stringResource(id = R.string.day_number_x, numberOfDay),
               style = MaterialTheme.typography.displaySmall
           )
           Text(
               text = stringResource(id = stretching.nameRes),
               style = MaterialTheme.typography.displayMedium
           )
           Image(
               painter = painterResource(id = stretching.imageRes),
               contentDescription = stringResource(id = stretching.descriptionRes),
               modifier = modifier.align(Alignment.CenterHorizontally)
           )
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = stringResource(R.string.stretching_description_title),
                   style = MaterialTheme.typography.labelSmall
               )
               Spacer(modifier = Modifier.weight(1f))
               StretchingButton(
                   expanded = expanded,
                   clickEvent = {
                       expanded = !expanded
                   }
               )
           }
           if(expanded) {
               Text(
                   text = stringResource(id = stretching.descriptionRes),
                   style = MaterialTheme.typography.bodyLarge
               )
           }
       }
    }
}

@Composable
private fun StretchingButton(
    expanded: Boolean,
    clickEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {
            clickEvent()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector =  if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StretchingAppTopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        },
        modifier = modifier
    )
}

@Preview(name = "Stretching Light Mode",showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Stretching Dark Mode",showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StretchingAppPreview() {
    DaysApp()
}