package com.villadevoto.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.villadevoto.data.Categories
import com.villadevoto.ui.util.VillaDevotoContentType

@Composable
fun VillaDevotoApp(windowSize: WindowWidthSizeClass) {
    val viewModel: VillaDevotoViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.CATEGORIES_SCREEN.name
    )

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> VillaDevotoContentType.LIST_ONLY
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> VillaDevotoContentType.LIST_NESTLED
        else -> VillaDevotoContentType.LIST_ONLY
    }

    Scaffold(
        topBar = {
            VillaDevotoTopBar(
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUpEvent = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        AppNavigation(viewModel, navController, uiState, innerPadding, contentType)

    }
}

@Composable
private fun AppNavigation(
    viewModel: VillaDevotoViewModel,
    navController: NavHostController,
    uiState: VillaDevotoAppUiState,
    innerPadding: PaddingValues,
    contentType: VillaDevotoContentType
) {
    NavHost(
        navController = navController,
        startDestination = Screens.CATEGORIES_SCREEN.name
    ) {
        composable(route = Screens.CATEGORIES_SCREEN.name) {
            VillaDevotoHomeScreen(
                categories = Categories.entries.toList(),
                categoryEvent = {
                    viewModel.updateCurrentCategory(it)
                    if (contentType == VillaDevotoContentType.LIST_ONLY) {
                        navController.navigate(Screens.RECOMMENDATIONS_SCREEN.name)
                    }
                },
                contentType = contentType,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                recommendationEvent = {
                    viewModel.updateCurrentPlace(it)
                    navController.navigate(Screens.RECOMMENDATIONS_SCREEN.name)
                },
                uiState = uiState
            )
        }
        composable(route = Screens.RECOMMENDATIONS_SCREEN.name) {
            VillaDevotoRecommendationsScreen(
                uiState = uiState,
                contentType = contentType,
                recommendationEvent = {
                    viewModel.updateCurrentPlace(it)
                    if (contentType == VillaDevotoContentType.LIST_ONLY) {
                        navController.navigate(Screens.RECOMMENDED_PLACE_SCREEN.name)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
        composable(route = Screens.RECOMMENDED_PLACE_SCREEN.name) {
            VillaDevotoRecommendedPlaceScreen(
                place = uiState.currentPlace,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VillaDevotoTopBar(
    canNavigateUp: Boolean,
    currentScreen: Screens,
    navigateUpEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = currentScreen.title))
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUpEvent) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}