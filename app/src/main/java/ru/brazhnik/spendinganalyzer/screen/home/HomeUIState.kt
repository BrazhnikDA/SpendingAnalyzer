package ru.brazhnik.spendinganalyzer.screen.home

import androidx.compose.runtime.State

internal sealed class HomeUIState {

    object Loading : HomeUIState()

    data class Success(val value: List<Item>) : HomeUIState()
}

data class Item(
    val title: String = "None",
    val price: Double = 0.0,
    val categories: CategoriesBuy = CategoriesBuy.NONE
)

enum class CategoriesBuy() {
    EVERY_MONTH,
    HEALTH,
    PRODUCTS,
    NONE
}