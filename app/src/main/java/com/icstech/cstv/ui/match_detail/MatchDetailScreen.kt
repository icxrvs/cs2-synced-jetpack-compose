package com.icstech.cstv.ui.match_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.icstech.cstv.R

@Composable
fun MatchDetailScreen(
    matchDetailViewModel: MatchDetailViewModel,
    matchId: String?,
    navController: NavController
) {
    val match by matchDetailViewModel.match.observeAsState(initial = null)
    val isLoading by matchDetailViewModel.isLoading.observeAsState(false)

    LaunchedEffect(Unit) {
        matchDetailViewModel.fetchMatch(matchId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
            val imagePainter = painterResource(id = R.drawable.team_logo)

            Column(
            ) {
                AsyncImage(
                    model = match?.opponents?.get(0)?.opponent?.image_url,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                Text("Time1", color = Color.White, textAlign = TextAlign.Center)
            }

            Text(text = "VS", color = Color.White, modifier = Modifier.padding(horizontal = 8.dp))

            Column() {
                AsyncImage(
                    model = match?.opponents?.get(1)?.opponent?.image_url,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                Text("Time2", color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            match?.begin_at?.let { Text(it, color = Color.White) }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}

//val imagePainter = painterResource(id = R.drawable.team_logo)
//Image(painter = imagePainter, contentDescription = "Descrição da imagem", modifier = Modifier.size(60.dp))