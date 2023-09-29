package com.icstech.csgomatchescompose.ui.match_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.icstech.csgomatchescompose.data.Player
import com.icstech.csgomatchescompose.util.convertUtcToLocalDate
import com.icstech.cstv.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchDetailScreen(
    matchDetailViewModel: MatchDetailViewModel,
    matchId: String?,
    navController: NavController
) {
    val match by matchDetailViewModel.match.observeAsState(initial = null)
    val isLoading by matchDetailViewModel.isLoading.observeAsState(false)
    val isLoadingOpponents by matchDetailViewModel.isLoadingOpponents.observeAsState(false)
    val opponents by matchDetailViewModel.opponents.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        matchDetailViewModel.fetchMatch(matchId)
        matchDetailViewModel.fetchOpponents(matchId)
    }

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.go_back))
            }

            Spacer(modifier = Modifier.width(8.dp))

            match?.opponents?.let{
                Text("${match?.opponents?.get(0)?.opponent?.name} X ${match?.opponents?.get(1)?.opponent?.name}", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, maxLines = 2,  overflow = TextOverflow.Ellipsis)
            }
        }

        if (isLoadingOpponents) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }else{

            Spacer(modifier = Modifier.height(10.dp))

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
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "VS")
                Spacer(modifier = Modifier.width(8.dp))
                Column{
                    AsyncImage(
                        model = match?.opponents?.get(1)?.opponent?.image_url,
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .align(CenterHorizontally)
            ) {
                match?.begin_at?.let { Text(convertUtcToLocalDate(it)) }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                Modifier
                    .align(CenterHorizontally)
            ) {
                Text("${match?.league?.name} ${match?.serie?.name}")
            }

            Spacer(modifier = Modifier.height(10.dp))

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

                                    )
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
private fun opponentsLeft(
    player: Player,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFC4C4C4)),
                contentAlignment = Alignment.Center
            ) {
                if (player.image_url != null) {
                    AsyncImage(
                        model = player.image_url,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("No image", textAlign = TextAlign.Center)
                }
            }
            Text(player.name)
        }
    }

}

@Composable
private fun opponentsRight(
    player: Player,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFC4C4C4)),
                contentAlignment = Alignment.Center
            ) {
                if (player.image_url != null) {
                    AsyncImage(
                        model = player.image_url,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("No image", textAlign = TextAlign.Center)
                }
            }
            Text(player.name)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMatchDetailScreen() {
    MatchDetailScreen(
        matchDetailViewModel = MatchDetailViewModel(),
        matchId = "Sample Match ID",
        navController = rememberNavController()
    )
}



