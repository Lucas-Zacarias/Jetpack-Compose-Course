package com.composebasics.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composebasics.R
import com.composebasics.ui.theme.BackgroundBusinessCard
import com.composebasics.ui.theme.ComposeBasicsTheme
import com.composebasics.ui.theme.TextBusinessCard

@Composable
fun BusinessCardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundBusinessCard)
    ) {
        Profile(
            Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )

        PersonalInfoCard(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )

    }
}

@Composable
private fun Profile(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.business_photo),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(R.string.name),
            fontSize = 28.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = stringResource(R.string.position),
            fontSize = 16.sp,
            color = TextBusinessCard,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PersonalInfoCard(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        PersonalInfo(Icons.Rounded.Call, stringResource(id = R.string.phone_number))

        PersonalInfo(Icons.Rounded.Share, stringResource(id = R.string.social_media))

        PersonalInfo(Icons.Rounded.Email, stringResource(id = R.string.email))
    }
}

@Composable
private fun PersonalInfo(
    icon: ImageVector,
    text: String
) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextBusinessCard,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    ComposeBasicsTheme {
        BusinessCardScreen()
    }
}