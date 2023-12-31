package com.icstech.csgomatchescompose.data.repository


import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.data.OpponentXX

class MatchRepository {
    private val matchService = RetrofitInstance.matchService
    suspend fun getMatches(currentDate: String): List<MatchItem> {
        return matchService.getMatches(currentDate)
    }
    suspend fun getMatchDetails(matchId: String?): MatchItem{
        return matchService.getMatchDetails(matchId = matchId)
    }
    suspend fun getOpponents(matchId: String?): OpponentXX {
        return matchService.getOpponents(matchId = matchId)
    }
}