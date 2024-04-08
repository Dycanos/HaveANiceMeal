package com.example.haveanicemeal.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.haveanicemeal.R
import com.example.haveanicemeal.ui.data.DataSource
import com.example.haveanicemeal.ui.data.Dish
import com.example.haveanicemeal.ui.theme.orangeHomePage

@Composable
fun SelectDishScreen(
    dishes: List<Dish>,
    onSelectionChanged: (Dish) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier)

{
    var selectedValue by rememberSaveable() { mutableStateOf("") }

    Column(modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(modifier = Modifier) {
            items(dishes) {
                var expanded by rememberSaveable { mutableStateOf(false) }
                val title = stringResource(id = it.title)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(Color.White),
                    border = BorderStroke(dimensionResource(id = R.dimen.border_card), orangeHomePage))
                {

                    Column(
                        modifier = Modifier.animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium)
                        )
                    )
                    {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_small))
                            .selectable(
                                selected = selectedValue == stringResource(id = it.title),
                                onClick = {
                                    selectedValue = title
                                    onSelectionChanged(it)
                                }),
                            verticalAlignment = Alignment.CenterVertically){

                            RadioButton(
                                selected = selectedValue == stringResource(id = it.title),
                                onClick = {
                                    selectedValue = title
                                    onSelectionChanged(it)
                                },
                                colors = RadioButtonDefaults.colors(orangeHomePage))
                            DishIcon(it.imageResourceId)
                            DishInformation(it.title,it.price)
                            Spacer(modifier = Modifier.weight(1f))
                            DishItemButton(
                                expanded = expanded,
                                onClick = { expanded = !expanded}
                            )}
                        if (expanded ){
                            DishIngredients(
                                dishIngredients = it.ingredients,
                                modifier = Modifier.padding(
                                    start = dimensionResource(R.dimen.padding_medium),
                                    top = dimensionResource(R.dimen.padding_small),
                                    end = dimensionResource(R.dimen.padding_medium),
                                    bottom = dimensionResource(R.dimen.padding_medium)
                                ))
                        }
                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(color = orangeHomePage,
                    text = stringResource(R.string.cancel))
            }
            Button(
                colors = ButtonDefaults.buttonColors(orangeHomePage),
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun DishIcon(@DrawableRes dishIcon: Int, modifier: Modifier = Modifier){
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.extraLarge),
        painter = painterResource(dishIcon),

        contentDescription = null
    )
}

@Composable
fun DishInformation(@StringRes dishTitle: Int, price:Int, modifier: Modifier = Modifier){

    Column(modifier = modifier) {
        Text(
            text = stringResource(dishTitle),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.euros, price),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun DishItemButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {

    IconButton(onClick = onClick ,modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(R.string.see_ingredients),
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun DishIngredients(@StringRes dishIngredients: Int,modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.ingredients),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(id = dishIngredients),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview
@Composable
fun DishItemPreview(){
    SelectDishScreen(
        dishes= DataSource.desserts,
        modifier=Modifier.fillMaxHeight()
    )
}