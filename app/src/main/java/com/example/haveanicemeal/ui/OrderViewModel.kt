package com.example.haveanicemeal.ui

import androidx.lifecycle.ViewModel
import com.example.haveanicemeal.ui.data.Dish
import com.example.haveanicemeal.ui.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()


    /**
     * Set the salad [salad] of menu for this order's state and update the price
     */
    fun setSalad(salad: Dish) {
        _uiState.update { currentState ->
            currentState.copy(
                salad = salad,
                price = salad.price.toString()
            )
        }
    }

    /**
     * Set the [dish] of menu for this order's state.
     * Only 1 dish can be selected for the whole order.
     */
    fun setDish(dish: Dish) {
        _uiState.update { currentState ->
            currentState.copy(
                dish = dish,
                price = currentState.price + dish.price
            )
        }
    }

    /**
     * Set the [dessert] for this order's state and update the price
     */
    fun setDessert(dessert: Dish) {
        _uiState.update { currentState ->
            currentState.copy(
                dessert = dessert,
                price = currentState.price + dessert.price
            )
        }
    }

    /**
     * Reset the order state
     */
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }
}