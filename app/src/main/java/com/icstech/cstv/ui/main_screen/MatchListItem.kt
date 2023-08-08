package com.icstech.cstv.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.icstech.cstv.data.MatchItem
import com.icstech.cstv.util.theme.DarkBlue2

@Composable
fun MatchListItem(
    data: MatchItem,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .height(176.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("matchDetailScreen/${data.id}") },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkBlue2,
        ),

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .graphicsLayer(
                        clip = true,
                        shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 16.dp)
                    )
                    .background(if (data.status == "running") Color.Red else Color.DarkGray)
            ){
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                ){
                    if (data.status != "running") Text(data.begin_at, color = Color.White) else Text(color = Color.White, text = "AGORA")
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
                        .padding(start = 73.5.dp, end = 73.5.dp, top = 18.5.dp, bottom = 18.5.dp) ,
                ) {
                        Row(
                            modifier = Modifier.fillMaxWidth() ,
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                AsyncImage(
                                    model = data.opponents?.get(0)?.opponent?.image_url,
                                    contentDescription = data.opponents?.get(0)?.opponent?.slug,
                                    modifier = Modifier.size(60.dp)
                                )
                                data.opponents?.get(0)?.opponent?.name?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                                        fontSize = 10.sp,
                                        color = Color.White
                                    )
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "VS",
                                    modifier = Modifier.padding(all = 8.dp),
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                AsyncImage(
                                    model = data.opponents?.get(1)?.opponent?.image_url,
                                    contentDescription = data.opponents?.get(1)?.opponent?.slug,
                                    modifier = Modifier.size(60.dp)
                                )
                                data.opponents?.get(1)?.opponent?.name?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                                        fontSize = 10.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
            ) {
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 11.5.dp)
                ){
                    Text(
                        text = "League: ${data.league.name}, Serie: ${data.serie.full_name}",
                        fontSize = 8.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

