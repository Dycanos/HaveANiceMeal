@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haveanicemeal

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.haveanicemeal.ui.HomePageScreen
import com.example.haveanicemeal.ui.OrderSummaryScreen
import com.example.haveanicemeal.ui.OrderViewModel
import com.example.haveanicemeal.ui.SelectDishScreen
import com.example.haveanicemeal.ui.data.DataSource
import androidx.lifecycle.viewmodel.compose.viewModel

enum class MealScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Salad(title = R.string.salad),
    Dish(title = R.string.dish),
    Dessert(title = R.string.dessert),
    Summary(title = R.string.order_summary)
}
/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun MealAppBar(
    currentScreen: MealScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Composable
fun MealApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MealScreen.valueOf(
        backStackEntry?.destination?.route ?: MealScreen.Start.name
    )
    Scaffold(
        topBar = {
            MealAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp()}
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MealScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = MealScreen.Start.name){
                HomePageScreen(
                    onNextButtonClicked = {
                        navController.navigate(MealScreen.Salad.name)
                                          },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = MealScreen.Salad.name){
                SelectDishScreen(
                    onNextButtonClicked = {
                        navController.navigate(MealScreen.Dish.name)
                    },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel,navController)},
                    dishes = DataSource.salads,
                    onSelectionChanged = {viewModel.setSalad(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MealScreen.Dish.name){
                SelectDishScreen(
                    onNextButtonClicked = {
                        navController.navigate(MealScreen.Dessert.name)
                    },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel,navController)},
                    dishes = DataSource.desserts,
                    onSelectionChanged = {viewModel.setDessert(it)},
                    modifier = Modifier.fillMaxHeight())
            }
            composable(route = MealScreen.Dish.name){
                SelectDishScreen(
                    onNextButtonClicked = {
                        navController.navigate(MealScreen.Summary.name)
                    },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel,navController)},
                    dishes = DataSource.dishes,
                    onSelectionChanged = {viewModel.setDish(it)},
                    modifier = Modifier.fillMaxHeight())
            }
            composable(route = MealScreen.Summary.name){
                val context= LocalContext.current
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel,navController)},
                    onSendButtonClicked = {subject:String,summary:String ->
                        shareOrder(context,subject,summary)
                    },
                    modifier = Modifier.fillMaxHeight())
            }
        }
    }
}


private fun cancelOrderAndNavigateToStart(viewModel: OrderViewModel,navController: NavController){

    viewModel.resetOrder()
    navController.popBackStack(MealScreen.Start.name, inclusive = false)
}

private fun shareOrder(context: Context, subject: String, summary: String){

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_order)
        )
    )


}

@Preview
@Composable
fun MealScreenPreview(){
}