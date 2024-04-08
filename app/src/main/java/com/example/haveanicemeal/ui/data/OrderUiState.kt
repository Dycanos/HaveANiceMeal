package com.example.haveanicemeal.ui.data

/**
 * Data class that represents the current UI state in terms of , [salad],
 * [dish],[dessert], selected pickup [price]
 */
data class OrderUiState(
    /** salad of the menu in the order (such as "Salad Cesar", "Salad niçoise", etc..) */
    val salad: Dish = Dish(0,0,0,0),
    /** dish of the menu in the order (such as "", "", etc..) */
    val dish: Dish = Dish(0,0,0,0),
    /** dessert of the menu in the order (such as "", "", etc..) */
    val dessert: Dish = Dish(0,0,0,0),
    /** Total price for the order */
    val price: Int = 0,
)