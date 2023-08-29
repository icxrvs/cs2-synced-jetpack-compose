package com.icstech.csgomatchescompose.ui.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.util.getCurrentDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var tabIndex by remember { mutableStateOf(1) }
    val tabs = listOf("Yesterday", "TODAY", "Select a date")

    val matches by viewModel.matches.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    fetchMatches(viewModel, getCurrentDate(true))

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Column(
                modifier = Modifier ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (Modifier.padding(15.dp)){
                    Text(text = "CSGOMatches")
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> fetchMatches(viewModel, getCurrentDate(isYesterdaySelected = true))
                    1 -> fetchMatches(viewModel, getCurrentDate())
                    2 -> null //fetchMatches(viewModel, "2023-08-09")
                }
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                MountList(matches, navController)
            }
        }
    }
}

@Composable
fun fetchMatches(viewModel: MainViewModel, currentDate: String) {
    LaunchedEffect(Unit) {
        viewModel.fetchMatches(currentDate)
    }
}

@Composable
fun MountList(
    matches: List<MatchItem>,
    navController: NavHostController
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(matches) { data ->
            if (data.opponents?.isNotEmpty() == true && data.opponents.size == 2){
                MatchListItem(matchItem = data, navController)
            }
        }
    }
}