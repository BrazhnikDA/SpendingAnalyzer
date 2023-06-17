package ru.brazhnik.spendinganalyzer.screen.home

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HomeScreen() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    when(uiState) {
        HomeUIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeUIState.Success -> {
            HomeScreenContent(
                data = uiState.value
            )
        }
    }
}

@Composable
private fun HomeScreenContent(data: List<Item>) {
    LazyColumn {
        itemsIndexed(data) { _, item ->
            ListItem(item.title, item.price)
        }
    }
}

@Composable
private fun ListItem(
    title: String,
    price: Double,
) {
    Row(
    ) {
        //Image(ColorDrawable(Color.Black.value.toInt()), contentDescription = "")
        Text(text = title)
        Text(text = price.toString())
    }
}
