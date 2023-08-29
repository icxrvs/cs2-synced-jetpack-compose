package com.icstech.csgomatchescompose.data.repository


import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.data.OpponentXX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchService {
    @GET("/matches?sort=begin_at&sort=status")
    suspend fun getMatches(@Query("filter[begin_at]") currentDate: String?): List<MatchItem>

    @GET("/matches/{matchId}")
    suspend fun getMatchDetails(@Path("matchId") matchId: String?): MatchItem

    @GET("/matches/{matchId}/opponents")
    suspend fun getOpponents(@Path("matchId") matchId: String?): OpponentXX
}