package com.example.haveanicemeal.ui.data

import com.example.haveanicemeal.R

object DataSource {

    val salads = listOf(
        Dish(R.drawable.salade_cesar, R.string.salade_cesar, 8, R.string.salad_cesar_ingredients),
        Dish(R.drawable.salade_grecque, R.string.salade_grecque, 7, R.string.salad_grecque_ingredients),
        Dish(R.drawable.salade_nicoise, R.string.salade_nicoise, 6, R.string.salad_nicoise_ingredients),
        Dish(R.drawable.salade_vosgienne, R.string.salade_vosgienne, 9, R.string.salad_vosgienne_ingredients)
    )


    val dishes = listOf(
        Dish(R.drawable.blanquette_de_veau,R.string.blanquette_de_veau,11, R.string.blanquette_de_veau_ingredients),
        Dish(R.drawable.bouchee_a_la_reine,R.string.bouch_e_la_reine,12, R.string.bouchee_a_la_reine_ingredients),
        Dish(R.drawable.lasagne,R.string.lasagne,12, R.string.lasagne_ingredients),
        Dish(R.drawable.pot_au_feu,R.string.pot_au_feu,15,R.string.pot_au_feu_ingredients)
    )


    val desserts = listOf(
        Dish(R.drawable.brownie,R.string.brownie,6,R.string.brownie_ingredients),
        Dish(R.drawable.cafe_gourmand,R.string.cafe_gourmand,6,R.string.cafe_gourmand_ingredients),
        Dish(R.drawable.creme_brulee,R.string.creme_brulee,6,R.string.creme_brulee_ingredients),
        Dish(R.drawable.dame_blanche,R.string.dame_blanche,6,R.string.dame_blanche_ingredients)
    )
}