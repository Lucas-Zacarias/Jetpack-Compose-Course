package com.bookshelf.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bookshelf.R
import kotlin.reflect.KFunction0

@Composable
fun BookshelfHome(
    bookshelfUiState: BookshelfUiState,
    retryEvent: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    when(bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookshelfUiState.Success -> BooksListScreen(
            books = bookshelfUiState.booksUrl,
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_small),
                    end = dimensionResource(id = R.dimen.padding_small),
                    top = dimensionResource(id = R.dimen.padding_small)
                ),
            paddingValues = paddingValues
        )
        is BookshelfUiState.Error -> ErrorScreen(
            retryEvent = retryEvent,
            modifier = modifier.fillMaxSize()
        )
        is BookshelfUiState.TitleNotFound -> TitleNotFoundScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun BooksListScreen(
    books: List<String>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.image_width_size)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier,
        contentPadding = paddingValues
    ) {
        items(items = books) { bookUrl ->
            BookItem(bookUrl)
        }
    }
}

@Composable
fun BookItem(
    bookUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model =
        ImageRequest
            .Builder(context = LocalContext.current)
            .data(bookUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        modifier = modifier.size(
            width = dimensionResource(id = R.dimen.image_width_size),
            height = dimensionResource(id = R.dimen.image_height_size)
        ),
        contentDescription = null
    )
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading_image),
        alignment = Alignment.Center,
        modifier = modifier.size(dimensionResource(id = R.dimen.image_size_medium))
    )
}

@Composable
fun ErrorScreen(
    retryEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error),
            modifier = Modifier.size(dimensionResource(id = R.dimen.image_size_medium))
        )
        Text(
            text = stringResource(id = R.string.connection_error),
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = retryEvent,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@Composable
fun TitleNotFoundScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_retry),
            contentDescription = stringResource(R.string.connection_error),
            modifier = Modifier.size(dimensionResource(id = R.dimen.image_size_medium))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(R.string.title_not_found_try_again_with_other),
            style = MaterialTheme.typography.titleLarge
        )
    }
}