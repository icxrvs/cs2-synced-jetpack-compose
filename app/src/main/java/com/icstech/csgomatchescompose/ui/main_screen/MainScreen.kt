package com.icstech.csgomatchescompose.ui.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.util.getCurrentDate
import com.icstech.csgomatchescompose.util.toUTCISOFormat
import com.icstech.cstv.R

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val matches by viewModel.matches.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    fetchMatches(viewModel, getCurrentDate())

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
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
                Spacer(modifier = Modifier.height(15.dp))
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
            if (data.opponents?.isNotEmpty() == true && data.opponents.size == 2) {
                MatchListItem(matchItem = data, navController)
            }
        }
    }
}