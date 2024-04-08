package com.example.haveanicemeal.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dish(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    val price: Int,
    @StringRes val ingredients: Int
)


