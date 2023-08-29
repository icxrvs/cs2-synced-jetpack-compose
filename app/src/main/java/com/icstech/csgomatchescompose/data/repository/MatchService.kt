package com.icstech.csgomatchescompose.data.repository


import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.data.OpponentXX
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchService {
    @GET("/matches?filter[begin_at]=2023-08-09&sort=begin_at&sort=status")
    suspend fun getMatches(): List<MatchItem>

    @GET("/matches/{matchId}")
    suspend fun getMatchDetails(@Path("matchId") matchId: String?): MatchItem

    @GET("/matches/{matchId}/opponents")
    suspend fun getOpponents(@Path("matchId") matchId: String?): OpponentXX
}