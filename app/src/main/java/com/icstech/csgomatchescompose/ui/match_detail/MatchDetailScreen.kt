package com.icstech.csgomatchescompose.ui.match_detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.icstech.csgomatchescompose.data.Player
import com.icstech.cstv.R

@Composable
fun MatchDetailScreen(
    matchDetailViewModel: MatchDetailViewModel,
    matchId: String?,
    navController: NavController
) {
    val match by matchDetailViewModel.match.observeAsState(initial = null)
    val isLoading by matchDetailViewModel.isLoading.observeAsState(false)
    val opponents by matchDetailViewModel.opponents.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        matchDetailViewModel.fetchMatch(matchId)
        matchDetailViewModel.fetchOpponents(matchId)
    }

    Column(
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.go_back), tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("${match?.league?.name} ${match?.serie?.name}", color = Color.White)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                AsyncImage(
                    model = match?.opponents?.get(0)?.opponent?.image_url,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                match?.opponents?.get(0)?.opponent?.name?.let { Text(it, color = Color.White, textAlign = TextAlign.Center) }
            }

            Text(text = "VS", color = Color.White, modifier = Modifier.padding(horizontal = 8.dp))

            Column{
                AsyncImage(
                    model = match?.opponents?.get(1)?.opponent?.image_url,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                match?.opponents?.get(1)?.opponent?.name?.let { Text(it, color = Color.White) }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            match?.begin_at?.let { Text(it, color = Color.White) }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 15.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 24.dp)
                ) {
                    opponents?.opponents?.get(0)?.let {
                        items(it.players) { item ->
                            opponentsLeft(
                                player = item,
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 12.dp,
                                    bottomEnd = 12.dp,
                                    bottomStart = 0.dp
                                ),
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 15.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 24.dp)
                ) {
                    opponents?.opponents?.get(1)?.let {
                        items(it.players) { item ->
                            opponentsRight(
                                player = item,
                                shape = RoundedCornerShape(
                                    topStart = 12.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 0.dp,
                                    bottomStart = 12.dp
                                ),
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun opponentsLeft(
    shape: Shape,
    player: Player,
    context: Context = LocalContext.current.applicationContext,
) {
    Card(
        modifier = Modifier.fillMaxSize().fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, player.name, Toast.LENGTH_SHORT)
                    .show()
            },
        shape
    ) {
            Row {
                Text(
                    text = player.name,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(48.49.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color(0xFFC4C4C4))
                ) {
                    AsyncImage(
                        model = player.image_url,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
}




@Composable
private fun opponentsRight(
    shape: Shape,
    player: Player,
    context: Context = LocalContext.current.applicationContext,
) {
    Card(
        modifier = Modifier.fillMaxSize().fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, player.name, Toast.LENGTH_SHORT)
                    .show()
            },
        shape
    ) {
        Row {
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 10.dp)
                    .size(48.49.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFC4C4C4))
            ) {
                AsyncImage(
                    model = player.image_url,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = player.name,
                fontSize = 16.sp
            )
        }
    }
}



