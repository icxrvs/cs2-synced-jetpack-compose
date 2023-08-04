package com.icstech.cstv.data.repository


import com.icstech.cstv.data.MatchItem
import retrofit2.http.GET

interface MatchService {
    @GET("/csgo/matches")
    suspend fun getMatches(): List<MatchItem>
}