package com.icstech.cstv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icstech.cstv.R
import com.icstech.cstv.ui.home.HomeViewModel
import com.icstech.cstv.ui.home.MainScreen
import com.icstech.cstv.util.theme.CSTVTheme

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CSTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }
}
@Composable
fun MountList(
    viewModel: HomeViewModel
) {
    val matches by viewModel.matches.observeAsState(emptyList())

    LaunchedEffect(Unit) {

        viewModel.fetchMatches()
    }

    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(matches) { data ->
            MainScreen(data = data)
        }
    }
}
@Composable
fun Greeting(viewModel: HomeViewModel) {
    Column{
        Row(modifier = Modifier.padding(top = 24.dp, start = 24.dp)) {
            Text(
                text = stringResource(R.string.matches),
                fontSize = 32.sp,
                color = Color.White,
            )
        }
        Row {
            MountList(viewModel)
        }
    }
}
