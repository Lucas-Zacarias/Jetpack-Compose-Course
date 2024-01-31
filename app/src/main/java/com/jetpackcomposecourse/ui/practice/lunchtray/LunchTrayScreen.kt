package com.jetpackcomposecourse.ui.practice.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.DataSource
import com.jetpackcomposecourse.data.model.OrderUiState
import com.jetpackcomposecourse.ui.theme.LunchTrayTheme

enum class LunchTrayScreen(@StringRes val title: Int) {
    START(title = R.string.lunch_tray_app_name),
    ENTREE_MENU(title = R.string.choose_entree),
    SIDE_DISH_MENU(title = R.string.choose_side_dish),
    ACCOMPANIMENT_MENU(title = R.string.choose_accompaniment),
    CHECKOUT(title = R.string.order_checkout)
}

@Composable
fun LunchTrayApp() {
    val viewModel: OrderViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.START.name
    )

    LunchTrayTheme {
        Scaffold(
            topBar = {
                LunchTrayAppBar(
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()
            AppNavigation(viewModel, navController, uiState, innerPadding)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LunchTrayAppBar(
    canNavigateBack: Boolean,
    currentScreen: LunchTrayScreen,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title)
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
private fun AppNavigation(
    viewModel: OrderViewModel,
    navController: NavHostController,
    uiState: OrderUiState,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = LunchTrayScreen.START.name
    ) {
        composable(route = LunchTrayScreen.START.name) {
            StartOrderScreen(
                onStartOrderButtonClicked = { navController.navigate(LunchTrayScreen.ENTREE_MENU.name) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
        composable(route = LunchTrayScreen.ENTREE_MENU.name) {
            EntreeMenuScreen(
                options = DataSource.entreeMenuItems,
                onCancelButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.SIDE_DISH_MENU.name)
                },
                onSelectionChanged = {
                    viewModel.updateEntree(it)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            )
        }
        composable(route = LunchTrayScreen.SIDE_DISH_MENU.name) {
            SideDishMenuScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.ACCOMPANIMENT_MENU.name)
                },
                onSelectionChanged = {
                    viewModel.updateSideDish(it)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            )
        }
        composable(route = LunchTrayScreen.ACCOMPANIMENT_MENU.name) {
            AccompanimentMenuScreen(
                options = DataSource.accompanimentMenuItems,
                onCancelButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.CHECKOUT.name)
                },
                onSelectionChanged = {
                    viewModel.updateAccompaniment(it)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            )
        }
        composable(route = LunchTrayScreen.CHECKOUT.name) {
            CheckoutScreen(
                orderUiState = uiState,
                onNextButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
                },
                onCancelButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
            )
        }
    }
}
