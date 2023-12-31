package com.icstech.csgomatchescompose.ui.main_screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.util.convertUtcToLocalDate
import com.icstech.csgomatchescompose.util.theme.md_theme_light_secondary

@Composable
fun MatchListItem(
    matchItem: MatchItem,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("matchDetailScreen/${matchItem.id}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_light_secondary)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
            ) {
                Row {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(convertUtcToLocalDate(matchItem.begin_at), color = Color.White)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 35.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = matchItem.opponents?.getOrNull(0)?.opponent?.image_url,
                                contentDescription = matchItem.opponents?.get(0)?.opponent?.slug,
                                modifier = Modifier.size(60.dp)
                            )
                            matchItem.opponents?.getOrNull(0)?.opponent?.name?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                        .fillMaxWidth(),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "X",
                                modifier = Modifier.padding(all = 8.dp),
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center, // Centraliza verticalmente
                            horizontalAlignment = Alignment.CenterHorizontally // Alinha horizontalmente
                        ) {
                            AsyncImage(
                                model = matchItem.opponents?.getOrNull(1)?.opponent?.image_url,
                                contentDescription = matchItem.opponents?.get(1)?.opponent?.slug,
                                modifier = Modifier.size(60.dp)
                            )
                            matchItem.opponents?.getOrNull(1)?.opponent?.name?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                        .fillMaxWidth(),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

