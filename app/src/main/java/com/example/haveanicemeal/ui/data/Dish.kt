package com.example.haveanicemeal.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.haveanicemeal.R

data class Dish(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    val price: Int,
    @StringRes val ingredients: Int
)


val salads = listOf(
    Dish(R.drawable.salade_cesar, R.string.salade_cesar, 8, R.string.salad_cesar_ingredients),
    Dish(R.drawable.salade_grecque, R.string.salade_grecque, 7, R.string.salad_grecque_ingredients),
    Dish(R.drawable.salade_nicoise,R.string.salade_nicoise, 6, R.string.salad_nicoise_ingredients),
    Dish(R.drawable.salade_vosgienne,R.string.salade_vosgienne, 9, R.string.salad_vosgienne_ingredients)
)


/*val dishes = listOf(
    Dish(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
    Dish(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
    Dish(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
    Dish(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4)
)


val desserts = listOf(
    Dish(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
    Dish(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
    Dish(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
    Dish(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4)
)*/