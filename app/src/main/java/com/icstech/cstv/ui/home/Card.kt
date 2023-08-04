package com.icstech.cstv.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icstech.cstv.data.MatchItem
import com.icstech.cstv.util.theme.DarkBlue2


@Composable
fun CardMatch(
    data: MatchItem,
) {
    Card(
        modifier = Modifier
            .height(176.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkBlue2,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        data.opponents.forEach{
                            Text(
                                text = it.opponent.name,
                                modifier = Modifier.padding(all = 8.dp),
                                color = Color.White
                            )
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
