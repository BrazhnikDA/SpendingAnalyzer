package ru.brazhnik.spendinganalyzer.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.brazhnik.spendinganalyzer.screen.home.HomeScreen

@ExperimentalPagerApi
@Composable
fun TabViewNavigation(
    navController: NavController
) {
    val rememberPagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            println(it.toString())
            TabPage(tabItems = tabs, pagerState = rememberPagerState)
        },
        bottomBar = {
            TabRow(selectedTabIndex = rememberPagerState.currentPage) {
                tabs.forEachIndexed { index, tabItem ->
                    Tab(selected = index == rememberPagerState.currentPage, onClick = {
                        coroutineScope.launch {
                            rememberPagerState.animateScrollToPage(tabItem.index)
                        }
                    }, text = {
                        Text(text = tabItem.title)
                    }, icon = {
                        Icon(tabItem.icon, "")
                    })
                }
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
fun TabPage(pagerState: PagerState, tabItems: List<TabItem>) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState
    ) { index ->
        tabItems[index].screenToLoad()
    }
}

private val tabs = listOf(
    TabItem.Home,
    TabItem.Calendar,
    TabItem.NewShopping,
    TabItem.ConfigurationType
)

sealed class TabItem(
    val index: Int,
    val icon: ImageVector,
    val title: String,
    val screenToLoad: @Composable () -> Unit
) {
    object Home : TabItem(0, Icons.Default.Home, "Home", {
        HomeScreen()
    })

    object Calendar : TabItem(1, Icons.Default.Settings, "Calendar", {
        HomeScreen()
    })

    object NewShopping : TabItem(2, Icons.Default.List, "New shopping", {
        HomeScreen()
    })

    object ConfigurationType : TabItem(3, Icons.Default.List, "Configuration", {
        HomeScreen()
    })
}