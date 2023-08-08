package com.icstech.cstv.data.repository


import com.icstech.cstv.data.MatchItem

class MatchRepository {
    private val matchService = RetrofitInstance.matchService
    suspend fun getMatches(): List<MatchItem> {
        return matchService.getMatches()
    }

    suspend fun getMatch(matchId: String?): MatchItem{
        return matchService.getMatch(matchId = matchId)
    }
}