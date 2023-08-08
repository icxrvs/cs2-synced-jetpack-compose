package com.icstech.cstv.data.repository


import com.icstech.cstv.data.MatchItem
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchService {
    @GET("/matches?filter[begin_at]=2023-08-08&sort=begin_at&sort=status&page=1&per_page=50")
    suspend fun getMatches(): List<MatchItem>

    @GET("/matches/{matchId}")
    suspend fun getMatch(@Path("matchId") matchId: String?): MatchItem
}