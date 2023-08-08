package com.icstech.cstv.ui.main_screen

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.icstech.cstv.R
import com.icstech.cstv.data.MatchItem

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {

    val matches by viewModel.matches.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.fetchMatches()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(modifier = Modifier.padding(top = 24.dp, start = 24.dp)) {
                Text(
                    text = stringResource(R.string.matches),
                    fontSize = 32.sp,
                    color = Color.White,
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
                MountList(matches, navController)
            }
        }
    }
}

@Composable
fun MountList(
    matches: List<MatchItem>,
    navController: NavHostController
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(matches) { data ->
            if (data.opponents?.isNotEmpty() == true){
                MatchListItem(data = data, navController)
            }
        }
    }
}