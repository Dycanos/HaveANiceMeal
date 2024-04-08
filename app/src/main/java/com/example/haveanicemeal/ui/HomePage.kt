package com.example.haveanicemeal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.haveanicemeal.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.haveanicemeal.ui.theme.orangeHomePage

/**
 * @author Louis
 * @param modifier : le modifier
 *
 * Composable which print the home page
 */
@Composable
fun HomePageScreen(
    onNextButtonClicked: ()->Unit,
    modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.background(Color.White)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
        ) {
            Image(
                painter = painterResource(id = R.drawable.meal_time),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.width_logo))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            )
            Text(
                text = stringResource(R.string.choisi_ton_menu),
                style = MaterialTheme.typography.headlineMedium,
                color = orangeHomePage,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { onNextButtonClicked },
            colors = ButtonDefaults.buttonColors(orangeHomePage),
            modifier = Modifier.width(dimensionResource(id = R.dimen.width_logo))
                .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.decouvre),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
                )
        }

    }
}


/**
 * @author Louis
 * Allows you to see the home page in Design Menu
 */
@Preview
@Composable
fun HomePagePreview(){
    HomePageScreen({},modifier = Modifier.fillMaxSize())
}